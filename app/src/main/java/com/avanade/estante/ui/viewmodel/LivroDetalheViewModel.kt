package com.avanade.estante.ui.viewmodel

import com.avanade.estante.domain.usecase.GetLivroPorIdUseCase
import com.avanade.estante.domain.usecase.GetResenhaUseCase
import com.avanade.estante.domain.usecase.CriarResenhaUseCase
import com.avanade.estante.domain.usecase.AtualizarResenhaUseCase
import com.avanade.estante.domain.usecase.DeletarResenhaUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.avanade.estante.ui.uistate.LivroDetalheUiState
import com.avanade.estante.ui.intent.LivroDetalheIntent
import com.avanade.estante.domain.model.Resenha

class LivroDetalheViewModel(
    private val getLivroPorIdUseCase: GetLivroPorIdUseCase,
    private val getResenhaUseCase: GetResenhaUseCase,
    private val criarResenhaUseCase: CriarResenhaUseCase,
    private val atualizarResenhaUseCase: AtualizarResenhaUseCase,
    private val deletarResenhaUseCase: DeletarResenhaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        LivroDetalheUiState()
    )

    val uiState: StateFlow<LivroDetalheUiState> =
        _uiState.asStateFlow()

    private var livroId: Int = 0

    private val usuarioId: Int = 1 // substituir pelo usuário logado

    fun onIntent(intent: LivroDetalheIntent) {

        when (intent) {

            is LivroDetalheIntent.CarregarLivro -> {
                livroId = intent.livroId
                carregarLivro()
            }

            is LivroDetalheIntent.SalvarResenha -> {
                salvarResenha(intent)
            }

            LivroDetalheIntent.DeletarResenha -> {
                deletarResenha()
            }

            LivroDetalheIntent.ErrorShown -> {
                _uiState.update {
                    it.copy(erro = null)
                }
            }
        }
    }

    private fun carregarLivro() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    erro = null
                )
            }

            try {

                val livro = getLivroPorIdUseCase(livroId)

                if (livro == null) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            erro = "Livro não encontrado"
                        )
                    }
                    return@launch
                }

                val resenha = getResenhaUseCase(
                    usuarioId = usuarioId,
                    livroId = livroId
                )

                _uiState.update {
                    it.copy(
                        livro = livro,
                        resenha = resenha,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        erro = "Não foi possível carregar o livro"
                    )
                }
            }
        }
    }

    private fun salvarResenha(
        intent: LivroDetalheIntent.SalvarResenha
    ) {

        viewModelScope.launch {

            try {

                val resenhaAtual = _uiState.value.resenha

                val resenha = Resenha(
                    id = resenhaAtual?.id ?: 0,
                    livroId = livroId,
                    usuarioId = usuarioId,
                    status = intent.status,
                    texto = intent.texto,
                    avaliacao = intent.avaliacao
                )

                if (resenhaAtual == null) {
                    criarResenhaUseCase(resenha)
                } else {
                    atualizarResenhaUseCase(resenha)
                }

                _uiState.update {
                    it.copy(
                        resenha = resenha
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        erro = "Não foi possível salvar a resenha"
                    )
                }
            }
        }
    }

    private fun deletarResenha() {

        viewModelScope.launch {

            val resenha = _uiState.value.resenha
                ?: return@launch

            try {

                deletarResenhaUseCase(resenha)

                _uiState.update {
                    it.copy(
                        resenha = null
                    )
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        erro = "Não foi possível excluir a resenha"
                    )
                }
            }
        }
    }
}
