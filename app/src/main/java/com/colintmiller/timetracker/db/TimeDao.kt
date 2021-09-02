package com.colintmiller.timetracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.colintmiller.timetracker.model.TimeLog
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeDao {

    @Query("Select * from TimeLog")
    fun getAll(): Flow<List<TimeLog>>

    @Query("Select * from TimeLog where endTime is null")
    fun getUnending(): Flow<List<TimeLog>>

    @Insert
    fun insertTime(log: TimeLog)

    @Update
    fun updateTime(log: TimeLog)
}