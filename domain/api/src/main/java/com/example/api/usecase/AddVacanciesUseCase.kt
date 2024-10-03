package com.example.api.usecase

import com.example.models.Vacancy

interface AddVacanciesUseCase  {
    suspend operator fun invoke(listVacancy: List<Vacancy>)
}