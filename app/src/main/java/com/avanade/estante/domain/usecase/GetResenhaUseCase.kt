package com.avanade.estante.domain.usecase

import com.avanade.estante.domain.model.Resenha
import com.avanade.estante.domain.repository.ResenhaRepository

class GetResenhaUseCase(
    private val repository: ResenhaRepository
) {
    suspend operator fun invoke(
        usuarioId: Int,
        livroId: Int
    ): Resenha? {
        return repository.getResenha(
            usuarioId = usuarioId,
            livroId = livroId
        )
    }
}
