package com.colintmiller.timetracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TimeLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startTime: Date? = Date(),
    val endTime: Date? = null)