package com.senmonb.vocabify.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.generativelanguage.v1beta3.GenerateTextRequest
import com.google.ai.generativelanguage.v1beta3.TextPrompt
import com.google.ai.generativelanguage.v1beta3.TextServiceClient
import com.google.ai.generativelanguage.v1beta3.TextServiceSettings
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider
import com.google.api.gax.rpc.FixedHeaderProvider
import com.google.gson.Gson
import com.senmonb.vocabify.PaLM_KEY
import com.senmonb.vocabify.data.Learn
import com.senmonb.vocabify.data.LearnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddVocabularyViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val learnRepository: LearnRepository,
    ) : ViewModel() {
        private var learnUiState by mutableStateOf(LearnUiState())

        // UI表示用のstate
        private val _output = MutableStateFlow(value = "")
        val output: StateFlow<String>
            get() = _output

        // リクエスト送信のための変数
        private var client: TextServiceClient

        // clientの初期化
        init {
            // Initialize the Text Service Client
            client =
                initializeTextServiceClient(
                    apiKey = PaLM_KEY,
                )
        }

        fun sendPrompt(inputText: String) {
            val inputTextPrompt =
                """
                 Generate several words related to $inputText and their Japanese translations. Output the generated words in the following json format. The Japanese translations should be generated in Japanese.
                 
                {"words": [
                   {"id" : "1", "word" : "word1", "translation" : "japanese translation1" },
                   {"id" : "2", "word" : "word2", "translation" : "japanese translation2" },
                   ...
                   ]}
                 
                 
                """.trimIndent()
            // Create the text prompt
            val prompt = createPrompt(inputTextPrompt)

            // Send the first request
            val request = createTextRequest(prompt)
            generateText(request)
        }

        // テキスト サービス クライアントの初期化
        private fun initializeTextServiceClient(apiKey: String): TextServiceClient {
            // (This is a workaround because GAPIC java libraries don't yet support API key auth)
            val transportChannelProvider =
                InstantiatingGrpcChannelProvider.newBuilder()
                    .setHeaderProvider(
                        FixedHeaderProvider.create(
                            hashMapOf(
                                "x-goog-api-key" to PaLM_KEY,
                            ),
                        ),
                    )
                    .build()

            // Create TextServiceSettings
            val settings =
                TextServiceSettings.newBuilder()
                    .setTransportChannelProvider(transportChannelProvider)
                    .setCredentialsProvider(FixedCredentialsProvider.create(null))
                    .build()

            // Initialize a TextServiceClient
            val textServiceClient = TextServiceClient.create(settings)

            return textServiceClient
        }

        // テキスト プロンプトの作成
        private fun createPrompt(textContent: String): TextPrompt {
            return TextPrompt.newBuilder()
                .setText(textContent)
                .build()
        }

        // テキストを生成
        private fun createTextRequest(prompt: TextPrompt): GenerateTextRequest {
            return GenerateTextRequest.newBuilder()
                .setModel("models/text-bison-001") // Required, which model to use to generate the result
                .setPrompt(prompt) // Required
                .setTemperature(0.5f) // Optional, controls the randomness of the output
                .setCandidateCount(1) // Optional, the number of generated texts to return
                .build()
        }

        // リクエスト送信

        private fun generateText(request: GenerateTextRequest) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = client.generateText(request)
                    var returnedText = response.candidatesList.last().output.trimMargin()
                    val removeHeadText = "```json".trimMargin()
                    val removeBottomText = "```"
                    returnedText = returnedText.removePrefix(removeHeadText)
                    returnedText = returnedText.removeSuffix(removeBottomText)

                    Log.d("debug-----", "$returnedText")

                    // display the returned text in the UI

                    val elementList = Gson().fromJson(returnedText, LearnStateList::class.java)
                    val element =
                        elementList.words.forEach {
                            // 　このコメントアウトを外せばroomに登録されるようになる
//                    learnRepository.insertLearn(it.toLearn())
                            Log.d("debug-----2", "$it}")
                        }
                    Log.d("debug-----", "$elementList")
                } catch (e: Exception) {
                    Log.d("debug-----", "$e")

                    // There was an error, let's add a new text with the details
                    _output.update { "API Error: ${e.message}" }
                }
            }
        }

        suspend fun insertLearn() {
            //  viewModelScope.launch{}
            learnRepository.insertLearn(learnUiState.learnState.toLearn())
        }
    }

data class LearnUiState(
    val learnState: LearnState = LearnState(),
)

data class LearnState(
    val id: Int = 0,
    val word: String = "",
    val translation: String = "",
)

data class LearnStateList(
    val words: List<LearnState>,
)

fun LearnState.toLearn(): Learn =
    Learn(
        id = id,
        word = word,
        translation = translation,
        isLook = 0,
    )

fun Learn.toLearnUiState(): LearnUiState =
    LearnUiState(
        learnState = this.toLearnState(),
    )

fun Learn.toLearnState(): LearnState =
    LearnState(
        id = 0,
        word = "",
        translation = "",
    )
