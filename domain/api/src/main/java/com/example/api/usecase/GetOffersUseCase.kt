package com.example.api.usecase

import com.example.models.Offers

interface GetOffersUseCase {
    suspend fun invoke(): Offers?
}