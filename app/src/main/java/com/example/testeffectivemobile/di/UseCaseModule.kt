package com.example.testeffectivemobile.di

import com.example.api.usecase.GetOffersUseCase
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
}
