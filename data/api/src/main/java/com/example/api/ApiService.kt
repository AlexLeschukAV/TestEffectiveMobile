package com.example.api

import com.example.models.Offers
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    fun getOffers(): Call<Offers>
}