package com.example.api.usecase

import com.example.models.Offers
import kotlinx.coroutines.flow.Flow

interface GetOffersUseCase {
    operator fun invoke(): Flow<Offers?>
}