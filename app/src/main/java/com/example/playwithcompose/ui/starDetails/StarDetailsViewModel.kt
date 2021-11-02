package com.example.playwithcompose.ui.starDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithcompose.BuildConfig
import com.example.playwithcompose.data.useCases.GetStarUseCase
import com.example.playwithcompose.ui.KEY_STAR_ID
import com.example.playwithcompose.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarDetailsViewModel @Inject constructor(
    private val getStarUseCase: GetStarUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Int>(KEY_STAR_ID)?.let { starId ->
            getStar(starId)
        }
    }

    private val _state = mutableStateOf(StarDetailsState())
    val state: State<StarDetailsState> get() = _state


    private fun getStar(starId: Int, apiKey: String = BuildConfig.API_KEY) {
        viewModelScope.launch {
            getStarUseCase(starId, apiKey).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = StarDetailsState(
                            star = result.data
                        )
                    }
                    is Result.Loading -> {
                        _state.value = StarDetailsState(
                            isLoading = true
                        )
                    }
                    is Result.Error -> {
                        _state.value = StarDetailsState(
                            error = result.message ?: "Something went wrong, please try again later"
                        )
                    }
                }
            }
        }
    }
}