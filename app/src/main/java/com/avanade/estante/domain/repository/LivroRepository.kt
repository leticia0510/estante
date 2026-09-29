package com.avanade.estante.domain.repository

import com.avanade.estante.domain.model.Livro

interface LivroRepository {

    suspend fun getTodosLivros(): List<Livro>

    suspend fun getLivroPorId(id: Int): Livro?

    suspend fun criarLivro(livro: Livro)

    suspend fun atualizarLivro(livro: Livro)

    suspend fun deletarLivro(id: Int)

    suspend fun getLivrosPorGenero(genero: String): List<Livro>

    suspend fun getLivrosPorAno(ano: Int): List<Livro>
}