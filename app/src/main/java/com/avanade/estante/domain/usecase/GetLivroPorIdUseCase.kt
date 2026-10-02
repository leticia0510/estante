package com.avanade.estante.domain.usecase

import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.repository.LivroRepository

class GetLivroPorIdUseCase(
    private val repository: LivroRepository
) {
    suspend operator fun invoke(id: Int): Livro? {
        return repository.getLivroPorId(id)
    }
}