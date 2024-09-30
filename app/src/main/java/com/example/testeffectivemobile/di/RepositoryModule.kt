package com.example.testeffectivemobile.di

import com.example.api.OffersRepository
import com.example.impl.repository.OffersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindGetOffersRepository(impl: OffersRepositoryImpl): OffersRepository
}