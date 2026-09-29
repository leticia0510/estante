package com.avanade.estante.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.avanade.estante.data.local.entity.LivroEntity

@Dao
interface LivroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun criarLivro(livro: LivroEntity)

    @Update
    suspend fun atualizarLivro(livro: LivroEntity)

    @Query("SELECT * FROM livros WHERE id = :id")
    suspend fun getLivroPorId(id: Int): LivroEntity?

    @Query("SELECT * FROM livros")
    suspend fun getTodosLivros(): List<LivroEntity>

    @Query("SELECT * FROM livros WHERE genero = :genero")
    suspend fun getLivroPorGenero(genero: String): List<LivroEntity>

    @Query("DELETE FROM livros WHERE id = :id")
    suspend fun deletarLivro(id: Int)

    @Query("SELECT * FROM livros WHERE anoPublicacao = :ano")
    suspend fun getLivroPorAno(ano: Int): List<LivroEntity>
}