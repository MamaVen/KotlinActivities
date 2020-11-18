package com.example.uiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class secondpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondpage)


        val preferences = getSharedPreferences("sharedPrefs", 0)
        val first_name = preferences.getString("fname", "")
        val last_name = preferences.getString("lname", "")
        val email_name = preferences.getString("email", "")
        val pnum_value = preferences.getString("pnum", "")
        val age_value = preferences.getString("ageval", "")
        val birth_date = preferences.getString("calendar", "")

        val fname = findViewById<TextView>(R.id.first_name_home)
        val lname = findViewById<TextView>(R.id.last_name_home)
        val email = findViewById<TextView>(R.id.email_add_home)
        val pnum = findViewById<TextView>(R.id.phone_num_home)
        val ageVal = findViewById<TextView>(R.id.age_home)
        val birth = findViewById<TextView>(R.id.birthDate_home)

        fname.text = "First Name: $first_name"
        lname.text = "Last Name: $last_name"
        email.text = "Email: $email_name"
        pnum.text = "Phone Number: $pnum_value"
        ageVal.text = "Age: $age_value"
        birth.text = "Birthday: $birth_date"
    }
}




