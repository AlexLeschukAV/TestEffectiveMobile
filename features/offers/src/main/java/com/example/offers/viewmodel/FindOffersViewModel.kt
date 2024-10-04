package com.example.offers.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.api.usecase.AddVacanciesUseCase
import com.example.api.usecase.GetOffersUseCase
import com.example.base.BaseViewModel
import com.example.offers.contract.OffersContract.Action
import com.example.offers.contract.OffersContract.Effect
import com.example.offers.contract.OffersContract.State
import com.example.models.Vacancy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindOffersViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val addVacanciesUseCase: AddVacanciesUseCase,
) : BaseViewModel<State, Action, Effect>() {

    init {
        getOffers()
    }

    private fun getOffers() {
        viewModelScope.launch {
            getOffersUseCase().collect { offers ->
                offers?.let {
                    setState {
                        it.copy(
                            isLoading = false,
                            offers = offers,
                        )
                    }
                }
            }
        }
    }

    private fun addVacancies(list: List<Vacancy>) {
        viewModelScope.launch { addVacanciesUseCase.invoke(list) }
    }

    private fun setLike(id: String) {
        setState { it.copy(offers = it.offers?.updateFavoriteVacancy(id)) }
    }

    private fun showAllVacancy(){
        setState { it.copy(offers = it.offers?.updateShowAllVacancy()) }
    }

    override fun initialState(): State = State()

    override fun handleEvent(action: Action) {
        when (action) {
            is Action.OnClickLike -> setLike(action.id)
            is Action.AddVacancies -> addVacancies(action.list)
            Action.OnShowAllVacancy -> showAllVacancy()
        }
    }
}