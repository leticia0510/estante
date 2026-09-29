package com.avanade.estante.domain.usecase

import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.repository.LivroRepository

class GetTodosLivrosUseCase(
    private val repository: LivroRepository
) {

    suspend operator fun invoke(): List<Livro> {
        return repository.getTodosLivros()
    }
}
