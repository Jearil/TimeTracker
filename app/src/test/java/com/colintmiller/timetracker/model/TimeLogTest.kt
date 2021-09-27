package com.colintmiller.timetracker.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

/**
 * Tests for the TimeLog class. Mostly for duration
 */
class TimeLogTest {

    @Test
    fun durationIsAccurate() {
        val timeLog = TimeLog(startTime = Date.from(GregorianCalendar(2021, 9, 26, 7, 0, 0).toZonedDateTime().toInstant()),
        endTime = Date.from(GregorianCalendar(2021, 9, 26, 9, 10, 23).toZonedDateTime().toInstant()))
        val duration = timeLog.duration()

        assertThat(duration).contains("2h 10m 23s")
    }

    @Test
    fun durationIsAccurateWhenSmall() {
        val timeLog = TimeLog(startTime = Date.from(GregorianCalendar(2021, 9, 26, 9, 0, 0).toZonedDateTime().toInstant()),
            endTime = Date.from(GregorianCalendar(2021, 9, 26, 9, 0, 23).toZonedDateTime().toInstant()))
        val duration = timeLog.duration()


        assertThat(duration).contains("23s")
    }
}