package com.example.favourites.contract

import com.example.base.MviAction
import com.example.base.MviEffect
import com.example.base.MviState
import com.example.models.Vacancy

object FavoriteVacancyContract {
    sealed interface Action : MviAction {
        data class OnClickLike(val id: String) : Action
    }

    data class State(
        val vacancy: List<Vacancy>? = null
    ) : MviState

    sealed interface Effect : MviEffect
}