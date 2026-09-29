package com.avanade.estante.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avanade.estante.domain.usecase.GetTodosLivrosUseCase
import com.avanade.estante.ui.intent.LivroListaIntent
import com.avanade.estante.ui.uistate.LivroListaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LivroListaViewModel(
    private val getTodosLivrosUseCase: GetTodosLivrosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LivroListaUiState())

    val uiState: StateFlow<LivroListaUiState> =
        _uiState.asStateFlow()

    fun onIntent(intent: LivroListaIntent) {

        when (intent) {

            LivroListaIntent.CarregarLivros,
            LivroListaIntent.RecarregarLivros -> {
                carregarLivros()
            }

            is LivroListaIntent.LivroClicked -> {
                // Futuramente podemos navegar para detalhes/edição
            }

            LivroListaIntent.ErrorShown -> {
                _uiState.update {
                    it.copy(erro = null)
                }
            }
        }
    }

    private fun carregarLivros() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    erro = null
                )
            }

            try {

                val livros = getTodosLivrosUseCase()

                _uiState.update {
                    it.copy(
                        livros = livros,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        erro = "Não foi possível carregar os livros"
                    )
                }
            }
        }
    }
}
