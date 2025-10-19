package com.example.playground.app.common.presentation.screen

import androidx.lifecycle.ViewModel
import com.example.playground.app.common.core.helper.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        val listOfTiles = listOf(
            "NEWS"
        )
        _uiState.update { state ->
            state.copy(
                tiles = listOfTiles
            )
        }
    }

    fun onIntent(intent: HomeIntents) {
        when (intent) {
            is HomeIntents.NewsTileClicked -> {

            }
        }
    }

}