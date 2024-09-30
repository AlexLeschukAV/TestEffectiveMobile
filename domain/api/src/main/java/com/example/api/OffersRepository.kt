package com.example.api

import com.example.models.Offers

interface OffersRepository {
    suspend fun getOffers(): Offers?
}