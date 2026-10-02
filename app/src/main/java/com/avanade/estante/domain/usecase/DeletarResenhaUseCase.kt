package com.avanade.estante.domain.usecase

import com.avanade.estante.domain.model.Resenha
import com.avanade.estante.domain.repository.ResenhaRepository

class DeletarResenhaUseCase(
    private val repository: ResenhaRepository
) {
    suspend operator fun invoke(resenha: Resenha) {
        repository.deletarResenha(resenha)
    }
}
