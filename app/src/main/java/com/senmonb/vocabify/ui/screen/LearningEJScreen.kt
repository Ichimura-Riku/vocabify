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


object LearningEJDestination : NavigationDestination {
    override val route = "learningEnglishJapanese"
    override val titleRes = R.string.learningEJ
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningEJScreen(
    modifier: Modifier = Modifier,
    viewModel: LearningEJViewModel = hiltViewModel(),
)  {
    Scaffold { innerPadding ->
        LearningEJContainer(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        )
    }
}

@Composable
fun LearningEJContainer(modifier: Modifier = Modifier)  {
    Text(text = "add vocabulary screen")
}