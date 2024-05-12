package com.example.taskmanager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ReminderAdapter(private var notes: List<Note>, private val context: Context) :
    RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    private val db = NotesDatabaseHelper(context)

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        init {
            updateButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val note = notes[position]
                    val intent = Intent(context, Update::class.java).apply {
                        putExtra("reminder_id", note.id)
                    }
                    context.startActivity(intent)
                }
            }

            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val note = notes[position]
                    db.deleteReminder(note.id)
                    refreshData(db.getAllReminders())
                    Toast.makeText(itemView.context, "Task Deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reminder_item, parent, false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val note = notes[position]
        holder.contentTextView.text = note.content
        holder.titleTextView.text = note.title
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    // Method to update the dataset and refresh the adapter
    fun refreshData(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
