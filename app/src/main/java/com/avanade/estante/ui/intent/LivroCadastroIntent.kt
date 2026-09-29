package com.avanade.estante.ui.intent

sealed interface LivroCadastroIntent {

    data class TituloChanged(
        val titulo: String
    ) : LivroCadastroIntent

    data class AutorChanged(
        val autor: String
    ) : LivroCadastroIntent

    data class AnoPublicacaoChanged(
        val ano: String
    ) : LivroCadastroIntent

    data class GeneroChanged(
        val genero: String
    ) : LivroCadastroIntent

    data class ImagemChanged(
        val urlImagem: String
    ) : LivroCadastroIntent

    data object CadastrarClicked : LivroCadastroIntent

    data object ErrorShown : LivroCadastroIntent

    data object CadastroSuccessHandled : LivroCadastroIntent
}
