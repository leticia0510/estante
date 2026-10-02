package com.avanade.estante.domain.repository

import com.avanade.estante.domain.model.LeituraStatus
import com.avanade.estante.domain.model.Resenha

interface ResenhaRepository {

    suspend fun criarResenha(resenha: Resenha)

    suspend fun atualizarResenha(resenha: Resenha)

    suspend fun getResenha(
        usuarioId: Int,
        livroId: Int
    ): Resenha?

    suspend fun getResenhasPorStatus(
        status: LeituraStatus
    ): List<Resenha>

    suspend fun deletarResenha(resenha: Resenha)
}
