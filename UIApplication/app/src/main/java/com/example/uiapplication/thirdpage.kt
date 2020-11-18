package com.example.uiapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast

class thirdpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirdpage)



        findViewById<Button>(R.id.btn_complete).setOnClickListener {signUp()}

        val calendar = findViewById<CalendarView>(R.id.calendarView)
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            val msg = ""+ (month + 1) + "/" + dayOfMonth + "/" + year
            val preferences = getSharedPreferences("sharedPrefs", 0)
            val editor = preferences.edit()
            editor.putString("calendar", msg)
            editor.commit()
        }
    }

    private fun signUp(){
        val intent = Intent(this, thirdpage::class.java)
        startActivity(intent)
    }
}