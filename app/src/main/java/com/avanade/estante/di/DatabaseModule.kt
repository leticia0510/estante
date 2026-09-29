package com.avanade.estante.di

import androidx.room.Room
import com.avanade.estante.data.local.AppDatabase
import com.avanade.estante.data.local.DatabaseSeeder
import com.avanade.estante.data.local.dao.LivroDao
import com.avanade.estante.data.local.dao.ResenhaDao
import com.avanade.estante.data.local.dao.UsuarioDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    single<LivroDao> {
        get<AppDatabase>().livroDao()
    }

    single<UsuarioDao> {
        get<AppDatabase>().usuarioDao()
    }

    single<ResenhaDao> {
        get<AppDatabase>().resenhaDao()
    }

    single {
        DatabaseSeeder(
            usuarioDao = get(),
            livroDao = get()
        )
    }
}
