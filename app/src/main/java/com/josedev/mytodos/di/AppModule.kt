package com.josedev.mytodos.di

import android.content.Context
import androidx.room.Room
import com.josedev.mytodos.data.ToDoDatabase
import com.josedev.mytodos.domain.repository.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //DB
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): ToDoDatabase {
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "todos.db"
        ).build()
    }

    // DAO
    @Provides
    @Singleton
    fun provideToDoDao(toDoDatabase: ToDoDatabase): ToDoDao {
        return toDoDatabase.todoDao
    }
}