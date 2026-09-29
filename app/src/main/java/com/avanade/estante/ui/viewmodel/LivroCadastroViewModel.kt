package com.avanade.estante.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.usecase.CriarLivroUseCase
import com.avanade.estante.ui.intent.LivroCadastroIntent
import com.avanade.estante.ui.uistate.LivroCadastroUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LivroCadastroViewModel(
    private val criarLivroUseCase: CriarLivroUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LivroCadastroUiState())

    val uiState: StateFlow<LivroCadastroUiState> =
        _uiState.asStateFlow()

    fun onIntent(intent: LivroCadastroIntent) {

        when (intent) {

            is LivroCadastroIntent.TituloChanged -> {
                _uiState.update {
                    it.copy(
                        titulo = intent.titulo,
                        erro = null
                    )
                }
            }

            is LivroCadastroIntent.AutorChanged -> {
                _uiState.update {
                    it.copy(
                        autor = intent.autor,
                        erro = null
                    )
                }
            }

            is LivroCadastroIntent.AnoPublicacaoChanged -> {
                _uiState.update {
                    it.copy(
                        anoPublicacao = intent.ano,
                        erro = null
                    )
                }
            }

            is LivroCadastroIntent.GeneroChanged -> {
                _uiState.update {
                    it.copy(
                        genero = intent.genero,
                        erro = null
                    )
                }
            }

            is LivroCadastroIntent.ImagemChanged -> {
                _uiState.update {
                    it.copy(
                        urlImagem = intent.urlImagem,
                        erro = null
                    )
                }
            }

            LivroCadastroIntent.CadastrarClicked -> {
                cadastrarLivro()
            }

            LivroCadastroIntent.ErrorShown -> {
                _uiState.update {
                    it.copy(erro = null)
                }
            }

            LivroCadastroIntent.CadastroSuccessHandled -> {
                _uiState.update {
                    it.copy(cadastroSucesso = false)
                }
            }
        }
    }

    private fun cadastrarLivro() {

        val state = _uiState.value

        if (
            state.titulo.isBlank() ||
            state.autor.isBlank() ||
            state.anoPublicacao.isBlank() ||
            state.genero.isBlank()
        ) {
            _uiState.update {
                it.copy(
                    erro = "Preencha todos os campos"
                )
            }

            return
        }

        val ano = state.anoPublicacao.toIntOrNull()

        if (ano == null) {
            _uiState.update {
                it.copy(
                    erro = "Digite um ano válido"
                )
            }

            return
        }

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    erro = null
                )
            }

            try {

                val livro = Livro(
                    id = 0,
                    titulo = state.titulo,
                    autor = state.autor,
                    urlImagem = state.urlImagem,
                    anoPublicacao = ano,
                    genero = state.genero
                )

                criarLivroUseCase(livro)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        cadastroSucesso = true
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        erro = "Ocorreu um erro ao cadastrar o livro"
                    )
                }
            }
        }
    }
}
