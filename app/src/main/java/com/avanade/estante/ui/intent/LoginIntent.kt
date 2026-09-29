package com.avanade.estante.ui.intent

sealed interface LoginIntent {

    data class EmailChanged(
        val email: String
    ) : LoginIntent

    data class SenhaChanged(
        val senha: String
    ) : LoginIntent

    data object LoginClicked : LoginIntent

    data object ErrorShown : LoginIntent

    data object LoginSuccessHandled : LoginIntent
}