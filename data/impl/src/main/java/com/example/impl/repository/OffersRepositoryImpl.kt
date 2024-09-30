package com.example.impl.repository

import com.example.api.ApiService
import com.example.api.OffersRepository
import com.example.models.Offers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
): OffersRepository {

    override suspend fun getOffers(): Offers? {
        val call = apiService.getOffers()
        var offers: Offers? = null
        call.enqueue(object : Callback<Offers> {
            override fun onResponse(call: Call<Offers>, response: Response<Offers>) {
               if (response.isSuccessful) {
                   offers = response.body()
                    // Обработка успешного ответа
                    offers?.let {
                        // Например, вывести список предложений
                        println(it.offers)
                    }
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
        return offers
    }
}