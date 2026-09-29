package com.avanade.estante.domain.model

data class Livro(
    val id: Int,
    val titulo: String,
    val autor: String,
    val urlImagem: String,
    val anoPublicacao: Int,
    val genero: String
)
