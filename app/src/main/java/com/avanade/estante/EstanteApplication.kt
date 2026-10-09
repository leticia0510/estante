package com.avanade.estante

import android.app.Application
import com.avanade.estante.data.local.DatabaseSeeder
import com.avanade.estante.di.databaseModule
import com.avanade.estante.di.networkModule
import com.avanade.estante.di.repositoryModule
import com.avanade.estante.di.useCaseModule
import com.avanade.estante.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class EstanteApplication : Application() {

    override fun onCreate() {

        val databaseSeeder: DatabaseSeeder by inject()

        super.onCreate()

        startKoin {
            androidContext(this@EstanteApplication)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            databaseSeeder.seed()
        }
    }
}