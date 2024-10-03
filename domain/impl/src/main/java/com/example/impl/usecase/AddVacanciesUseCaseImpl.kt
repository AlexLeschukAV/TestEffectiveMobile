package com.example.impl.usecase

import com.example.api.OffersRepository
import com.example.api.usecase.AddVacanciesUseCase
import com.example.models.Vacancy
import javax.inject.Inject

class AddVacanciesUseCaseImpl @Inject constructor(
    private val repository: OffersRepository,
): AddVacanciesUseCase{
    override suspend fun invoke(listVacancy: List<Vacancy>) {
        repository.addVacancies(listVacancy)
    }
}