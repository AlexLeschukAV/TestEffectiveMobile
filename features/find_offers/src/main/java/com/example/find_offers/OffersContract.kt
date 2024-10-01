package com.example.find_offers

import com.example.base.MviAction
import com.example.base.MviEffect
import com.example.base.MviState
import com.example.models.Offers

object OffersContract {
    sealed interface Action: MviAction{
     data class OnClickLike(val id: String): Action
        object OnShowAllVacancy: Action
    }

    data class State(
        val isLoading: Boolean = true,
        val offers: Offers? = null,
    ): MviState

    sealed interface Effect: MviEffect {
        class ShowError(val message: String): Effect
    }
}