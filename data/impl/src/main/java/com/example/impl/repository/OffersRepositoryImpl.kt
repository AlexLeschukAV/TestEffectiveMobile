package com.example.impl.repository

import com.example.api.ApiService
import com.example.api.OffersRepository
import com.example.api.db.dao.DaoItems
import com.example.api.db.entity.toEntity
import com.example.models.Offers
import com.example.models.Vacancy
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val itemDao: DaoItems,
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

    override fun getAllFavoriteVacancy(): Flow<List<Vacancy>> {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteVacancy(vacancies: List<Vacancy>) {
     //   itemDao.updateVacancy(vacancies.map { it })
    }

    override suspend fun deleteFavoriteVacancy(item: Vacancy) {
        TODO("Not yet implemented")
    }
}