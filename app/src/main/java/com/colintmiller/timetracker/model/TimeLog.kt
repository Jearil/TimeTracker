package com.colintmiller.timetracker.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*
import java.util.concurrent.TimeUnit

@Entity
data class TimeLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val startTime: Date = Date(),
    var endTime: Date? = null) {

    @Ignore
    fun duration() : String {
        endTime?.let {
            val duration = it.time - startTime.time
            val hours = TimeUnit.MILLISECONDS.toHours(duration)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(duration)

            return if (hours > 0) {
                hours.toString() + "h " + minutes.toString() + "m " + seconds.toString() + "s"
            } else {
                minutes.toString() + "m " + seconds.toString() + "s"
            }
        }
        return ""
    }
}