package com.example.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.api.db.converter.EntityConverters
import com.example.api.db.dao.DaoItems
import com.example.api.db.entity.VacancyEntity


@Database(
    entities = [VacancyEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(EntityConverters::class)
abstract class AppDbRoom : RoomDatabase() {
    abstract fun daoItems(): DaoItems
}