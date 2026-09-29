package com.avanade.estante.di

import com.avanade.estante.data.repository.LivroRepositoryImpl
import com.avanade.estante.data.repository.UsuarioRepositoryImpl
import com.avanade.estante.domain.repository.LivroRepository
import com.avanade.estante.domain.repository.UsuarioRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<LivroRepositoryImpl>{
        LivroRepositoryImpl(
            livroDao = get()
        )
    }

    single<UsuarioRepository> {
        UsuarioRepositoryImpl(
            usuarioDao = get()
        )
    }

    single<LivroRepository> {
        LivroRepositoryImpl(
            livroDao = get()
        )
    }
}