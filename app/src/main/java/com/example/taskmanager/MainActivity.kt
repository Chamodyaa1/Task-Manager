package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var reminderAdapter: ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize database helper
        db = NotesDatabaseHelper(this)

        // Initialize RecyclerView and adapter
        reminderAdapter = ReminderAdapter(db.getAllReminders(), this)
        binding.remindersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.remindersRecyclerView.adapter = reminderAdapter

        // Set click listener for addButton
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddReminder::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh data when activity resumes
        reminderAdapter.refreshData(db.getAllReminders())
    }
}
