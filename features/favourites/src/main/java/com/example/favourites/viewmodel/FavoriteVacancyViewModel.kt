package com.example.favourites.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.api.usecase.AddVacanciesUseCase
import com.example.api.usecase.GetAllFavoriteVacancyUseCase
import com.example.base.BaseViewModel
import com.example.favourites.contract.FavoriteVacancyContract.State
import com.example.favourites.contract.FavoriteVacancyContract.Action
import com.example.favourites.contract.FavoriteVacancyContract.Effect
import com.example.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVacancyViewModel @Inject constructor(
    private val getAllFavoriteVacancyUseCase: GetAllFavoriteVacancyUseCase,
    private val addVacanciesUseCase: AddVacanciesUseCase,
) : BaseViewModel<State, Action, Effect>() {

    init {
        getVacancies()
    }

    override fun initialState(): State = State()

    override fun handleEvent(action: Action) {
        when (action) {
            is Action.OnClickLike -> {
                setLike(action.id)
                state.value.vacancy?.let {
                    addVacancies(it)
                }
            }
        }
    }

    private fun getVacancies() {
        viewModelScope.launch {
            getAllFavoriteVacancyUseCase.invoke().collect { list ->
                setState { it.copy(vacancy = list) }
            }
        }
    }

    private fun setLike(id: String) {
        setState {
            it.copy(vacancy = it.vacancy?.map { item -> item.updateFavoriteVacancy(id) })
        }
    }

    private fun addVacancies(list: List<Vacancy>) {
        viewModelScope.launch { addVacanciesUseCase.invoke(list) }
    }
}