package com.avanade.estante.data.repository

import com.avanade.estante.data.local.dao.LivroDao
import com.avanade.estante.data.local.entity.LivroEntity
import com.avanade.estante.domain.model.Livro
import com.avanade.estante.domain.repository.LivroRepository

class LivroRepositoryImpl(
    private val livroDao: LivroDao
) : LivroRepository {

    override suspend fun criarLivro(livro: Livro) {

        livroDao.criarLivro(
            LivroEntity(
                id = livro.id,
                titulo = livro.titulo,
                autor = livro.autor,
                urlImagem = livro.urlImagem,
                anoPublicacao = livro.anoPublicacao,
                genero = livro.genero
            )
        )
    }

    override suspend fun atualizarLivro(livro: Livro) {

        livroDao.atualizarLivro(
            LivroEntity(
                id = livro.id,
                titulo = livro.titulo,
                autor = livro.autor,
                urlImagem = livro.urlImagem,
                anoPublicacao = livro.anoPublicacao,
                genero = livro.genero
            )
        )
    }

    override suspend fun getLivroPorId(id: Int): Livro? {

        return livroDao.getLivroPorId(id)?.toDomain()
    }

    override suspend fun getTodosLivros(): List<Livro> {

        return livroDao
            .getTodosLivros()
            .map { it.toDomain() }
    }

    override suspend fun getLivrosPorGenero(
        genero: String
    ): List<Livro> {

        return livroDao
            .getLivroPorGenero(genero)
            .map { it.toDomain() }
    }

    override suspend fun getLivrosPorAno(
        ano: Int
    ): List<Livro> {

        return livroDao
            .getLivroPorAno(ano)
            .map { it.toDomain() }
    }

    override suspend fun deletarLivro(id: Int) {

        livroDao.deletarLivro(id)
    }

    private fun LivroEntity.toDomain(): Livro {

        return Livro(
            id = id,
            titulo = titulo,
            autor = autor,
            urlImagem = urlImagem,
            anoPublicacao = anoPublicacao,
            genero = genero
        )
    }
}
