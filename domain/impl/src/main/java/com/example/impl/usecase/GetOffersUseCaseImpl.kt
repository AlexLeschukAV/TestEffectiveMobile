package com.example.impl.usecase

import com.example.api.OffersRepository
import com.example.api.usecase.GetOffersUseCase
import com.example.models.Offers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOffersUseCaseImpl @Inject constructor(
    private val offersRepository: OffersRepository
) : GetOffersUseCase {
    override fun invoke(): Flow<Offers?> {
        return offersRepository.getOffers()
    }
}