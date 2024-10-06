package com.example.intentpractice.di

import com.example.intentpractice.api.Endpoint
import com.example.intentpractice.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Provides
    @Singleton
    fun provideRecipeApi(): Endpoint {
        return Retrofit.Builder()
            .baseUrl("https://api-receitas-at4n.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoint::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api: Endpoint): RecipeRepository {
        return RecipeRepository(api)
    }
}
