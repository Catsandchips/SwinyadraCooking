package com.slouchingdog.android.swinyadracooking.data.di

import com.slouchingdog.android.swinyadracooking.data.local.RecipeDAO
import com.slouchingdog.android.swinyadracooking.data.repository.RecipeRepositoryImpl
import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryImpl(dao: RecipeDAO): RecipeRepository {
        return RecipeRepositoryImpl(dao)
    }

}