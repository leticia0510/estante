package com.avanade.estante.ui.uistate

import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.model.Resenha

data class LivroDetalheUiState(
    val livro: Livro? = null,
    val resenha: Resenha? = null,
    val isLoading: Boolean = false,
    val erro: String? = null
)
