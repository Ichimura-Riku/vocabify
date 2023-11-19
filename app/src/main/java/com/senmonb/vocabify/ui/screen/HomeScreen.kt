package com.senmonb.vocabify.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.senmonb.vocabify.R
import com.senmonb.vocabify.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Let's Learn Vocabulary")
        Column() {

            Button(onClick = { /*TODO*/ }) {
                Text(text = "add Vocabulary")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "learning English-Japanese")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "learning Japanese-English")
            }
        }
    }
}