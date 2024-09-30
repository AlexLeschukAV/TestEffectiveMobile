package com.example.impl.usecase

import com.example.api.OffersRepository
import com.example.api.usecase.GetOffersUseCase
import com.example.models.Offers
import javax.inject.Inject

class GetOffersUseCaseImpl @Inject constructor(
    private val offersRepository: OffersRepository
) : GetOffersUseCase {
    override suspend fun invoke(): Offers? {
        return offersRepository.getOffers()
    }
}