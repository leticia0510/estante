package com.avanade.estante.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.update
import com.avanade.estante.domain.usecase.LoginUseCase
import com.avanade.estante.ui.uistate.LoginUiState
import com.avanade.estante.ui.intent.LoginIntent

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun onIntent(intent: LoginIntent) {

        when (intent) {

            is LoginIntent.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = intent.email,
                        erro = null
                    )
                }
            }

            is LoginIntent.SenhaChanged -> {
                _uiState.update {
                    it.copy(
                        senha = intent.senha,
                        erro = null
                    )
                }
            }

            LoginIntent.LoginClicked -> {
                login()
            }

            LoginIntent.ErrorShown -> {
                _uiState.update {
                    it.copy(erro = null)
                }
            }

            LoginIntent.LoginSuccessHandled -> {
                _uiState.update {
                    it.copy(loginSucesso = false)
                }
            }
        }
    }

    private fun login() {

        val state = _uiState.value

        if (state.email.isBlank() || state.senha.isBlank()) {

            _uiState.update {
                it.copy(
                    erro = "Preencha todos os campos"
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

                val usuario = loginUseCase(
                    email = state.email,
                    senha = state.senha
                )

                if (usuario != null) {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            loginSucesso = true
                        )
                    }

                } else {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            erro = "Usuário não encontrado"
                        )
                    }
                }

            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        erro = "Ocorreu um erro ao fazer login"
                    )
                }
            }
        }
    }
}
