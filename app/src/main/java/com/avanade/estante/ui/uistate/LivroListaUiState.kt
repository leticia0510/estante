package com.avanade.estante.ui.uistate

import com.avanade.estante.domain.model.Livro

data class LivroListaUiState(
    val livros: List<Livro> = emptyList(),
    val isLoading: Boolean = false,
    val erro: String? = null
)
