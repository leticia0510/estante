package com.avanade.estante.ui.uistate

data class LoginUiState(
    val email: String = "",
    val senha: String = "",
    val isLoading: Boolean = false,
    val loginSucesso: Boolean = false,
    val erro: String? = null
)
