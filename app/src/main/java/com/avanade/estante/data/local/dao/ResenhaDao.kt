package com.avanade.estante.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.avanade.estante.data.local.entity.ResenhaEntity

@Dao
interface ResenhaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun criarResenha(resenha: ResenhaEntity)

    @Upsert
    suspend fun atualizarResenha(resenha: ResenhaEntity)

    @Query("SELECT * FROM resenhas WHERE usuarioId = :usuarioId AND livroId = :livroId")
    suspend fun getResenha(usuarioId: Int, livroId: Int): ResenhaEntity?

    @Delete
    suspend fun deletarResenha(resenha: ResenhaEntity)
}