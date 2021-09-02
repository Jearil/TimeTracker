package com.colintmiller.timetracker.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.formattedString(): String {
    val df = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.US)
    return df.format(this)
}