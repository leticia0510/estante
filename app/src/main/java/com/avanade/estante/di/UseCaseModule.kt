package com.avanade.estante.di

import com.avanade.estante.domain.usecase.AtualizarResenhaUseCase
import com.avanade.estante.domain.usecase.CriarLivroUseCase
import com.avanade.estante.domain.usecase.CriarResenhaUseCase
import com.avanade.estante.domain.usecase.DeletarResenhaUseCase
import com.avanade.estante.domain.usecase.GetLivroPorIdUseCase
import com.avanade.estante.domain.usecase.GetResenhaUseCase
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

    factory {
        GetLivroPorIdUseCase(
            repository = get()
        )
    }

    factory {
        GetResenhaUseCase(
            repository = get()
        )
    }

    factory {
        CriarResenhaUseCase(
            repository = get()
        )
    }

    factory {
        AtualizarResenhaUseCase(
            repository = get()
        )
    }

    factory {
        DeletarResenhaUseCase(
            repository = get()
        )
    }
}
