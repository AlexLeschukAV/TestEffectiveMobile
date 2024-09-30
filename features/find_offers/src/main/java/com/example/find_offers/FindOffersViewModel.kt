package com.example.find_offers

import androidx.lifecycle.ViewModel
import com.example.api.usecase.GetOffersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindOffersViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
): ViewModel() {

    private suspend fun getOffers() {
        val offers = getOffersUseCase
    }

}