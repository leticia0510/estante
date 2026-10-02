package com.avanade.estante.ui.intent

import com.avanade.estante.domain.model.LeituraStatus

sealed interface LivroDetalheIntent {

    data class CarregarLivro(
        val livroId: Int
    ) : LivroDetalheIntent

    data class SalvarResenha(
        val texto: String,
        val avaliacao: Int,
        val status: LeituraStatus
    ) : LivroDetalheIntent

    data object DeletarResenha : LivroDetalheIntent

    data object ErrorShown : LivroDetalheIntent
}
