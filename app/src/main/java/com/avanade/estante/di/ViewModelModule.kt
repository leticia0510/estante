package com.avanade.estante.di

import com.avanade.estante.ui.viewmodel.LivroCadastroViewModel
import com.avanade.estante.ui.viewmodel.LivroListaViewModel
import com.avanade.estante.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        LoginViewModel(
            loginUseCase = get()
        )
    }

    viewModel {
        LivroCadastroViewModel(
            criarLivroUseCase = get()
        )
    }

    viewModel {
        LivroListaViewModel(
            getTodosLivrosUseCase = get()
        )
    }
}
