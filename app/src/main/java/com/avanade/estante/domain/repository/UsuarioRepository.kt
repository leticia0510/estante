package com.avanade.estante.domain.repository

import com.avanade.estante.domain.model.Usuario

interface UsuarioRepository {

    suspend fun criarUsuario(usuario: Usuario)

    suspend fun login(email: String, senha: String): Usuario?

    suspend fun getUsuarioPorId(id: Int): Usuario?
}
