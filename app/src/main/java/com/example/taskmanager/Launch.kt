package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Launch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    fun main(view: View) {
        val intent = Intent(this, MainActivity  ::class.java)
        startActivity(intent)
    }
}