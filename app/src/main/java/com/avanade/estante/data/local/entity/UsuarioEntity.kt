package com.avanade.estante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String
)