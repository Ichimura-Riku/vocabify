package com.senmonb.vocabify.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.senmonb.vocabify.data.LearnRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LearningEJViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val learnRepository: LearnRepository,
): ViewModel() {
}