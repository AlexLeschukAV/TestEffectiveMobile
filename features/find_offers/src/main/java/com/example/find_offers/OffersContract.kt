package com.example.find_offers

import com.example.base.MviAction
import com.example.base.MviEffect
import com.example.base.MviState
import com.example.models.Offer
import com.example.models.Vacancy

object OffersContract {
    sealed interface Action: MviAction{

    }

    data class State(
        val isLoading: Boolean = true,
        val listOffers: List<Offer>? = null,
        val listVacancies: List<Vacancy>? = null,
    ): MviState

    sealed interface Effect: MviEffect {
        class ShowError(val message: String): Effect
    }
}