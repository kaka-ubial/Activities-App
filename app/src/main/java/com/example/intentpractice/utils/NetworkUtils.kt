package com.example.intentpractice.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkUtils {
    companion object {
        fun getRetrofitInstance(path: String): Retrofit {
            // Configura o cliente HTTP com timeouts
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // Tempo limite de conexão
                .readTimeout(30, TimeUnit.SECONDS) // Tempo limite de leitura
                .writeTimeout(30, TimeUnit.SECONDS) // Tempo limite de escrita
                .build()

            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient) // Adiciona o cliente HTTP ao Retrofit
                .build()
        }
    }
}
