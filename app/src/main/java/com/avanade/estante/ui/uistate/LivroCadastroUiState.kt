package com.avanade.estante.ui.uistate

data class LivroCadastroUiState(
    val titulo: String = "",
    val autor: String = "",
    val urlImagem: String = "",
    val anoPublicacao: String = "",
    val genero: String = "",
    val isLoading: Boolean = false,
    val cadastroSucesso: Boolean = false,
    val erro: String? = null
)
