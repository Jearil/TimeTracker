package com.colintmiller.timetracker

import android.app.Application
import com.colintmiller.timetracker.db.TimeDao
import com.colintmiller.timetracker.db.TimeDatabase

class TimeApp : Application() {
    private val database by lazy { TimeDatabase.getDatabase(this) }
    val dao by lazy { database.timeDao() }
}