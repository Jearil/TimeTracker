package com.colintmiller.timetracker.model

import com.colintmiller.timetracker.db.TimeDao
import java.util.*

class TimeLogManager(private val dao: TimeDao) {

    fun startStopReading() : Boolean {
        return if (isReadingInProgress()) {
            val unendingList = dao.getUnending()
            val inProgress = unendingList[0]
            inProgress.endTime = Date()

            dao.updateTime(inProgress)
            false;
        } else {
            val startReading = TimeLog()
            dao.insertTime(startReading)
            true;
        }
    }

    fun isReadingInProgress(): Boolean {
        val unending = dao.getUnending()
        return unending.isNotEmpty()
    }
}