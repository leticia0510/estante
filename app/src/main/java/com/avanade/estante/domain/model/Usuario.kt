package com.avanade.estante.domain.model

data class Usuario (
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String
)