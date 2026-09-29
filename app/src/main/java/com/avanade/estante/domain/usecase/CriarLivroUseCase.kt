package com.avanade.estante.domain.usecase

import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.repository.LivroRepository

class CriarLivroUseCase(
    private val repository: LivroRepository
) {

    suspend operator fun invoke(livro: Livro) {
        repository.criarLivro(livro)
    }
}
