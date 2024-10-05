package com.example.offers.contract

import com.example.base.MviAction
import com.example.base.MviEffect
import com.example.base.MviState
import com.example.models.Offers
import com.example.models.Vacancy

object OffersContract {
    sealed interface Action : MviAction {
        data object GetOffers: Action
        data class OnClickLike(val id: String) : Action
        data class AddVacancies(val list: List<Vacancy>) : Action
        data object OnShowAllVacancy: Action
    }

    data class State(
        val isLoading: Boolean = true,
        val offers: Offers? = null,
    ) : MviState

    sealed interface Effect : MviEffect {
        class ShowError(val message: String) : Effect
    }
}