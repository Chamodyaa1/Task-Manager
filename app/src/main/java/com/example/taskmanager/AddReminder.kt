package com.example.taskmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanager.databinding.ActivityAddReminderBinding

class AddReminder : AppCompatActivity() {
    private lateinit var binding: ActivityAddReminderBinding
    private lateinit var db: NotesDatabaseHelper

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstanceState = null)
        binding = ActivityAddReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        binding.save.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0, title, content)
            db.insertReminder(note)
            finish()
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }
    }
}