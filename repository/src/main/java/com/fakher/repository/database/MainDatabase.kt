package com.fakher.repository.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fakher.repository.model.FormPersistence

@Database(
    entities = [FormPersistence::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    companion object {

        private val LOCK = Any()
        private const val DATABASE_NAME = "main.db"

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(
            @NonNull
            context: Context
        ): MainDatabase {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            MainDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getFormDao(): FormDAO
}