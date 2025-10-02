package com.slouchingdog.android.swinyadracooking.domain.di

import com.slouchingdog.android.swinyadracooking.domain.RecipeRepository
import com.slouchingdog.android.swinyadracooking.domain.use_cases.AddRecipeUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.DeleteRecipeUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.GetRecipeByIdUseCase
import com.slouchingdog.android.swinyadracooking.domain.use_cases.GetRecipeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideAddRecipeUseCase(repository: RecipeRepository): AddRecipeUseCase {
        return AddRecipeUseCase(repository)
    }

    @Provides
    fun provideDeleteRecipeUseCase(repository: RecipeRepository): DeleteRecipeUseCase {
        return DeleteRecipeUseCase(repository)
    }

    @Provides
    fun provideGetRecipeByIdUseCase(repository: RecipeRepository): GetRecipeByIdUseCase {
        return GetRecipeByIdUseCase(repository)
    }

    @Provides
    fun provideGetRecipeListUseCase(repository: RecipeRepository): GetRecipeListUseCase {
        return GetRecipeListUseCase(repository)
    }


}