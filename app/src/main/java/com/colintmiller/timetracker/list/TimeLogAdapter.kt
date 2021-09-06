package com.colintmiller.timetracker.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.colintmiller.timetracker.R
import com.colintmiller.timetracker.model.TimeLog
import com.colintmiller.timetracker.utils.formattedString

class TimeLogAdapter : ListAdapter<TimeLog, TimeLogAdapter.TimeLogViewHolder>(TimeLogComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.time_item_layout, parent, false)
        return TimeLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimeLogViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class TimeLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val startTimeText = itemView.findViewById<TextView>(R.id.start_time)
        private val stopTimeText = itemView.findViewById<TextView>(R.id.end_time)
        private val duration = itemView.findViewById<TextView>(R.id.duration)

        fun bind(timeLog: TimeLog) {
            Log.e("colin", "Setting startTime to " + timeLog.startTime?.formattedString())
            Log.e("colin", "Setting endTime to " + timeLog.endTime?.formattedString())
            Log.e("colin", "Setting duration to " + timeLog.duration())
            startTimeText.text = timeLog.startTime?.formattedString()
            stopTimeText.text = timeLog.endTime?.formattedString()
            duration.text = timeLog.duration()
        }
    }

    class TimeLogComparator : DiffUtil.ItemCallback<TimeLog>() {
        override fun areItemsTheSame(oldItem: TimeLog, newItem: TimeLog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TimeLog, newItem: TimeLog): Boolean {
            return oldItem.id == newItem.id && oldItem.endTime == newItem.endTime
        }
    }
}