package com.avanade.estante.di

import com.avanade.estante.domain.usecase.CriarLivroUseCase
import com.avanade.estante.domain.usecase.GetTodosLivrosUseCase
import com.avanade.estante.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        LoginUseCase(
            repository = get()
        )
    }

    factory {
        CriarLivroUseCase(
            repository = get()
        )
    }

    factory {
        GetTodosLivrosUseCase(
            repository = get()
        )
    }
}
