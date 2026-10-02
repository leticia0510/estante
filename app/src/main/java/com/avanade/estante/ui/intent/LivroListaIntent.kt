package com.avanade.estante.ui.intent

sealed interface LivroListaIntent {

    data object CarregarLivros : LivroListaIntent

    data object RecarregarLivros : LivroListaIntent



    data object ErrorShown : LivroListaIntent
}
