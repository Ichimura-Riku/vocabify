package com.senmonb.vocabify.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
)  {
    Scaffold { innerPadding ->
        AddVocabularyContainer(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        )
    }
}

@Composable
fun AddVocabularyContainer(modifier: Modifier = Modifier)  {
    Text(text = "add vocabulary screen")
}
