package com.example.impl.repository

import android.util.Log
import com.example.api.ApiService
import com.example.api.OffersRepository
import com.example.api.db.dao.DaoItems
import com.example.api.db.entity.toEntity
import com.example.api.db.entity.toModel
import com.example.models.Offers
import com.example.models.Vacancy
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
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
                    Log.d("Error", "Ошибка: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Offers>, t: Throwable) {
                // Обработка ошибки сети
                Log.d("Error", "Ошибка: ${t.message}")
            }
        })
        awaitClose()
    }

    override fun getAllFavoriteVacancy(): Flow<List<Vacancy>> {
        return itemDao.getFavoriteVacancies(true).map { listEntity ->
            listEntity.map { it.toModel() }
        }
    }

    override suspend fun addVacancies(vacancies: List<Vacancy>) {
        val entity = vacancies.map { it.toEntity() }
        itemDao.insertVacancy(entity)
    }
}