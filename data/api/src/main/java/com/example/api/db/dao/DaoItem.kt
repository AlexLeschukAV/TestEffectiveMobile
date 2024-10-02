package com.example.api.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.api.db.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoItems {

    @Query("SELECT * FROM vacancies")
    fun getAllVacancies(): Flow<List<VacancyEntity>>

    @Update
    suspend fun updateVacancy(entity: List<VacancyEntity>)

    @Delete
    suspend fun deleteVacancy(entity: VacancyEntity)
}