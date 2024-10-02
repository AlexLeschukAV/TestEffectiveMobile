package com.example.api.di

import android.content.Context
import androidx.room.Room
import com.example.api.db.AppDbRoom
import com.example.api.db.dao.DaoItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext
        context: Context,
    ): AppDbRoom {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = AppDbRoom::class.java,
            name = BD_NAME
        ).build()
    }

    @Provides
    fun provideItemDao(
        database: AppDbRoom,
    ): DaoItems = database.daoItems()


    private const val BD_NAME = "em_vacancy.db"
}