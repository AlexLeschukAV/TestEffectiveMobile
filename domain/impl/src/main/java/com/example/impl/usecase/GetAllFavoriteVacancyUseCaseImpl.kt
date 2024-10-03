package com.example.impl.usecase

import com.example.api.OffersRepository
import com.example.api.usecase.GetAllFavoriteVacancyUseCase
import com.example.models.Vacancy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoriteVacancyUseCaseImpl @Inject constructor(
    private val repository: OffersRepository,
): GetAllFavoriteVacancyUseCase {
    override fun invoke(): Flow<List<Vacancy>> {
        return repository.getAllFavoriteVacancy()
    }
}