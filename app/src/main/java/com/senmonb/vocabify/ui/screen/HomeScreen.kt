package com.senmonb.vocabify.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    Scaffold { innerPadding ->
        HomeContainer(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        )

    }
}


@Composable
fun HomeContainer(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello Android!",
        modifier = modifier
    )
}