package com.colintmiller.timetracker.list

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
        return TimeLogViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TimeLogViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class TimeLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val startTimeText = itemView.findViewById<TextView>(R.id.start_time)
        private val stopTimeText = itemView.findViewById<TextView>(R.id.end_time)

        fun bind(timeLog: TimeLog) {
            startTimeText.text = timeLog.startTime?.formattedString()
            stopTimeText.text = timeLog.endTime?.formattedString()
        }
    }

    class TimeLogComparator : DiffUtil.ItemCallback<TimeLog>() {
        override fun areItemsTheSame(oldItem: TimeLog, newItem: TimeLog): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TimeLog, newItem: TimeLog): Boolean {
            return oldItem == newItem
        }
    }
}