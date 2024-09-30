package com.example.impl.repository

import com.example.api.ApiService
import com.example.api.OffersRepository
import com.example.models.Offers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : OffersRepository {

    override fun getOffers(): Flow<Offers?> = callbackFlow {
        val call = apiService.getOffers()
        call.enqueue(object : Callback<Offers> {
            override fun onResponse(call: Call<Offers>, response: Response<Offers>) {
                if (response.isSuccessful) {
                    trySend(response.body())
                } else {
                    // Обработка ошибки
                    println("Ошибка: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Offers>, t: Throwable) {
                // Обработка ошибки сети
                println("Ошибка: ${t.message}")
            }
        })
        awaitClose()
    }
}