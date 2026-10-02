package com.avanade.estante.data.repository

import com.avanade.estante.data.local.dao.ResenhaDao
import com.avanade.estante.data.local.entity.LeituraStatusEntity
import com.avanade.estante.data.local.entity.ResenhaEntity
import com.avanade.estante.domain.model.LeituraStatus
import com.avanade.estante.domain.model.Resenha
import com.avanade.estante.domain.repository.ResenhaRepository

class ResenhaRepositoryImpl(
    private val resenhaDao: ResenhaDao,
) : ResenhaRepository {

    override suspend fun criarResenha(
        resenha: Resenha
    ) {
        resenhaDao.criarResenha(
            ResenhaEntity(
                id = resenha.id,
                livroId = resenha.livroId,
                usuarioId = resenha.usuarioId,
                status = resenha.status.toEntity(),
                texto = resenha.texto,
                avaliacao = resenha.avaliacao
            )
        )
    }

    override suspend fun atualizarResenha(
        resenha: Resenha
    ) {
        resenhaDao.atualizarResenha(
            ResenhaEntity(
                id = resenha.id,
                livroId = resenha.livroId,
                usuarioId = resenha.usuarioId,
                status = resenha.status.toEntity(),
                texto = resenha.texto,
                avaliacao = resenha.avaliacao
            )
        )
    }

    override suspend fun getResenha(
        usuarioId: Int,
        livroId: Int
    ): Resenha? {
        return resenhaDao
            .getResenha(usuarioId, livroId)
            ?.toDomain()
    }

    override suspend fun getResenhasPorStatus(
        status: LeituraStatus
    ): List<Resenha> {

        return resenhaDao
            .getResenhasPorStatus(status.toEntity())
            .map { it.toDomain() }
    }

    override suspend fun deletarResenha(
        resenha: Resenha
    ) {
        resenhaDao.deletarResenha(
            ResenhaEntity(
                id = resenha.id,
                livroId = resenha.livroId,
                usuarioId = resenha.usuarioId,
                status = resenha.status.toEntity(),
                texto = resenha.texto,
                avaliacao = resenha.avaliacao
            )
        )
    }

    private fun ResenhaEntity.toDomain(): Resenha {
        return Resenha(
            id = id,
            livroId = livroId,
            usuarioId = usuarioId,
            status = status.toDomainModel(),
            texto = texto,
            avaliacao = avaliacao
        )
    }
}
