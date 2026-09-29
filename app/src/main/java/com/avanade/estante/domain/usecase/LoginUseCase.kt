package com.avanade.estante.domain.usecase

import com.avanade.estante.domain.model.Usuario
import com.avanade.estante.domain.repository.UsuarioRepository

class LoginUseCase(
    private val repository: UsuarioRepository
) {

    suspend operator fun invoke(
        email: String,
        senha: String
    ): Usuario? {
        return repository.login(
            email = email,
            senha = senha
        )
    }
}
