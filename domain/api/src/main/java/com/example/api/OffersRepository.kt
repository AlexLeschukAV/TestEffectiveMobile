package com.example.api

import com.example.models.Offers
import kotlinx.coroutines.flow.Flow

interface OffersRepository {
    fun getOffers(): Flow<Offers?>
}