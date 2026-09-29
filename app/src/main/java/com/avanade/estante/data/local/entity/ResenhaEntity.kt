package com.avanade.estante.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "resenhas",
    foreignKeys = [
        ForeignKey(
            entity = LivroEntity::class,
            parentColumns = ["id"],
            childColumns = ["livroId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UsuarioEntity::class,
            parentColumns = ["id"],
            childColumns = ["usuarioId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            value = ["usuarioId", "livroId"],
            unique = true
        )
    ]
)
data class ResenhaEntity(
    @PrimaryKey
    val id: Int,
    val livroId: Int,
    val usuarioId: Int,
    val texto: String,
    val avaliacao: Int
)
