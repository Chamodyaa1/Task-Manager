package com.example.taskmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanager.databinding.ActivityUpdateBinding

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("reminder_id", -1)
        if (noteId == -1) {
            finish()
            return
        }
        val note = db.getNoteById(noteId)
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.editsave.setOnClickListener {
            val newTitle = binding.updatetitleEditText.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.updateReminder(updateNote)
            finish()
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}
