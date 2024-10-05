package com.example.api.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.api.db.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoItems {

   @Query("SELECT * FROM vacancies WHERE isFavorite = :isFavorite")
    fun getFavoriteVacancies(isFavorite: Boolean): Flow<List<VacancyEntity>>

    @Insert(entity = VacancyEntity::class,
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(entity: List<VacancyEntity>)

}