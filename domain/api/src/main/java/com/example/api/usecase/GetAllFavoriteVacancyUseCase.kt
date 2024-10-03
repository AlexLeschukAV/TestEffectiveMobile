package com.example.api.usecase

import com.example.models.Vacancy
import kotlinx.coroutines.flow.Flow

interface GetAllFavoriteVacancyUseCase {
    operator fun invoke(): Flow<List<Vacancy>>
}