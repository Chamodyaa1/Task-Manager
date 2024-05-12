package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db :NotesDatabaseHelper
    private lateinit var ReminderAdapter:ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)
        ReminderAdapter = ReminderAdapter(db.getAllReminders(),this)

        binding.remindersRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.remindersRecyclerView.adapter = ReminderAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddReminder::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        ReminderAdapter.refreshData(db.getAllReminders())
    }
}
