package com.avanade.estante.data.local

import com.avanade.estante.data.local.dao.LivroDao
import com.avanade.estante.data.local.dao.UsuarioDao
import com.avanade.estante.data.local.entity.LivroEntity
import com.avanade.estante.data.local.entity.UsuarioEntity

class DatabaseSeeder(
    private val usuarioDao: UsuarioDao,
    private val livroDao: LivroDao
) {

    suspend fun seed() {

        inserirUsuarioFake()
        inserirLivrosFake()
    }

    private suspend fun inserirUsuarioFake() {

        val usuario = usuarioDao.getUsuarioPorId(1)

        if (usuario == null) {

            usuarioDao.criarUsuario(
                UsuarioEntity(
                    id = 1,
                    nome = "Usuário Teste",
                    email = "teste@email.com",
                    senha = "123456"
                )
            )
        }
    }

    private suspend fun inserirLivrosFake() {

        val livros = livroDao.getTodosLivros()

        if (livros.isEmpty()) {

            livroDao.criarLivro(
                LivroEntity(
                    id = 1,
                    titulo = "Dom Casmurro",
                    autor = "Machado de Assis",
                    urlImagem = "https://m.media-amazon.com/images/I/61x1ZHomWUL._AC_UF1000,1000_QL80_.jpg",
                    anoPublicacao = 1899,
                    genero = "Romance"
                )
            )

            livroDao.criarLivro(
                LivroEntity(
                    id = 2,
                    titulo = "O Hobbit",
                    autor = "J. R. R. Tolkien",
                    urlImagem = "https://m.media-amazon.com/images/I/91M9xPIf10L.jpg",
                    anoPublicacao = 1937,
                    genero = "Fantasia"
                )
            )

            livroDao.criarLivro(
                LivroEntity(
                    id = 3,
                    titulo = "1984",
                    autor = "George Orwell",
                    urlImagem = "https://m.media-amazon.com/images/I/61t0bwt1s3L._AC_UF1000,1000_QL80_.jpg",
                    anoPublicacao = 1949,
                    genero = "Ficção"
                )
            )
        }
    }
}
