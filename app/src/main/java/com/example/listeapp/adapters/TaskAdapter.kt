package com.example.listeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.listeapp.R
import com.example.listeapp.models.TaskItem

class TaskAdapter(context: Context, private val tasks: List<TaskItem>) :
    ArrayAdapter<TaskItem>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
            holder = ViewHolder()
            holder.nameTextView = view.findViewById(R.id.textViewTaskName)
            holder.descriptionTextView = view.findViewById(R.id.textViewTaskDescription)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val task = getItem(position)
        if (task != null) {
            holder.nameTextView.text = task.name
            holder.descriptionTextView.text = task.description
        }

        return view!!
    }

    private class ViewHolder {
        lateinit var nameTextView: TextView
        lateinit var descriptionTextView: TextView
    }
}