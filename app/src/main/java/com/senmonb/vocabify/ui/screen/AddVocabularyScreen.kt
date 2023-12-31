package com.senmonb.vocabify.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.senmonb.vocabify.R
import com.senmonb.vocabify.ui.navigation.NavigationDestination

object AddVocabularyDestination : NavigationDestination {
    override val route = "addVocabulary"
    override val titleRes = R.string.add_vocabulary
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVocabularyScreen(
    modifier: Modifier = Modifier,
    viewModel: AddVocabularyViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        AddVocabularyContainer(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            viewModel = viewModel,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVocabularyContainer(
    modifier: Modifier = Modifier,
    viewModel: AddVocabularyViewModel,
) {
    val (inputText, setInputText) = remember { mutableStateOf("") }
    val textOutput: String by viewModel.output.collectAsState()
    Column(
        modifier = Modifier.padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText,
            onValueChange = setInputText,
            label = { Text("input keyword") },
        )
        Button(
            onClick = {
                viewModel.sendPrompt(inputText)
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("entry")
        }
    }
}
