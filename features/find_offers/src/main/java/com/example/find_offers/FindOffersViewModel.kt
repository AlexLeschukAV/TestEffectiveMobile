package com.example.find_offers

import androidx.lifecycle.viewModelScope
import com.example.api.usecase.GetOffersUseCase
import com.example.base.BaseMviViewModel
import com.example.find_offers.OffersContract.Action
import com.example.find_offers.OffersContract.Effect
import com.example.find_offers.OffersContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindOffersViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
) : BaseMviViewModel<State, Action, Effect>() {

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

    private fun setLike(id: String) {
        setState { it.copy(offers = it.offers?.updateFavoriteVacancy(id)) }
    }

    private fun setShowAllVacancy(){
        setState { it.copy(offers = it.offers?.updateShowAllVacancy()) }
    }

    override fun initialState(): State = State()

    override fun handleEvent(action: Action) {
        when (action) {
            is Action.OnClickLike -> setLike(action.id)
            Action.OnShowAllVacancy -> setShowAllVacancy()
        }
    }
}