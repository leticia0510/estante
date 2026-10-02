package com.avanade.estante.domain.model

data class Resenha (
    val id: Int,
    val livroId: Int,
    val usuarioId: Int,
    val status: LeituraStatus,
    val texto: String,
    val avaliacao: Int
)