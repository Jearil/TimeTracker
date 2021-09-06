package com.colintmiller.timetracker.viewmodel

import androidx.lifecycle.*
import com.colintmiller.timetracker.db.TimeDao
import com.colintmiller.timetracker.model.TimeLog
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TimeLogViewModel(
    private val dao: TimeDao): ViewModel() {

    val allLogs : LiveData<List<TimeLog>> = dao.getAll().asLiveData()

    fun insert(timeLog: TimeLog) = viewModelScope.launch {
        dao.insertTime(timeLog)
    }
}

class TimeLogViewModelFactory(private val dao: TimeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimeLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimeLogViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}