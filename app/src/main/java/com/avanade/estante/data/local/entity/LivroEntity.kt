package com.avanade.estante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livros")
data class LivroEntity (
    @PrimaryKey
    val id: Int,
    val titulo: String,
    val autor: String,
    val urlImagem: String,
    val anoPublicacao: Int,
    val genero: String
)