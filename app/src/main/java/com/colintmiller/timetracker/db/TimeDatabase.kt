package com.colintmiller.timetracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.colintmiller.timetracker.model.TimeLog
import java.lang.NullPointerException

@Database(version = 1, entities = [TimeLog::class])
@TypeConverters(Converters::class)
abstract class TimeDatabase : RoomDatabase() {
    abstract fun timeDao() : TimeDao

    companion object {
        @Volatile
        private var INSTANCE : TimeDatabase? = null

        fun getDatabase(context: Context): TimeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TimeDatabase::class.java,
                    "timedb").build()
                INSTANCE = instance
                instance
            }
        }
    }
}