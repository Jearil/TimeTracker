package com.colintmiller.timetracker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.colintmiller.timetracker.list.TimeLogAdapter
import com.colintmiller.timetracker.model.TimeLogManager
import com.colintmiller.timetracker.viewmodel.TimeLogViewModel
import com.colintmiller.timetracker.viewmodel.TimeLogViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimeLogFragment : Fragment(R.layout.log_view_layout) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val uiScope = CoroutineScope(Dispatchers.Main)

    private lateinit var recyclerView : RecyclerView
    private lateinit var toggleButton: Button

    private val adapter: TimeLogAdapter = TimeLogAdapter()
    private lateinit var viewModel: TimeLogViewModel
    private lateinit var manager: TimeLogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val timeApp = requireContext().applicationContext as TimeApp
        viewModel = TimeLogViewModelFactory(timeApp.dao).create(TimeLogViewModel::class.java)
        manager = TimeLogManager(timeApp.dao)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = requireView().findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        toggleButton = requireView().findViewById(R.id.toggle_reading)

        viewModel.allLogs.observe(viewLifecycleOwner) {logs ->
            Log.e("colin", "We have items " + logs.size)
            logs.let { adapter.submitList(it) }
        }

        setupButton();
    }

    private fun setupButton() {
        ioScope.launch { setToggleText(manager.isReadingInProgress()) }

        toggleButton.setOnClickListener {
            ioScope.launch {
                val isReading = manager.startStopReading()
                setToggleText(isReading)
            }
        }
    }

    private fun setToggleText(isReading : Boolean) {
        uiScope.launch {
            if (isReading) {
                toggleButton.text = getText(R.string.stop)
            } else {
                toggleButton.text = getText(R.string.start)
            }
        }
    }
}