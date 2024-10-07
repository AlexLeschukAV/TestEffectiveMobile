package com.example.testheadhunter.di

import com.example.api.usecase.AddVacanciesUseCase
import com.example.api.usecase.GetAllFavoriteVacancyUseCase
import com.example.api.usecase.GetOffersUseCase
import com.example.impl.usecase.AddVacanciesUseCaseImpl
import com.example.impl.usecase.GetAllFavoriteVacancyUseCaseImpl
import com.example.impl.usecase.GetOffersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Singleton
    @Binds
    fun bindsGetOffersUseCase(impl: GetOffersUseCaseImpl): GetOffersUseCase

    @Singleton
    @Binds
    fun bindsAddFavoriteVacancyUseCase(impl: AddVacanciesUseCaseImpl): AddVacanciesUseCase

    @Singleton
    @Binds
    fun bindsGetAllFavoriteUseCase(impl: GetAllFavoriteVacancyUseCaseImpl): GetAllFavoriteVacancyUseCase
}
