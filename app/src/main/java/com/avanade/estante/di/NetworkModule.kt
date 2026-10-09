package com.avanade.estante.di

import com.avanade.estante.data.remote.BooksApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module
import com.avanade.estante.BuildConfig

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .url(
                        chain.request().url.newBuilder()
                            .addQueryParameter(
                                "key",
                                BuildConfig.API_BOOKS
                            )
                            .build()
                    )
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<BooksApi> {
        get<Retrofit>().create(BooksApi::class.java)
    }
}