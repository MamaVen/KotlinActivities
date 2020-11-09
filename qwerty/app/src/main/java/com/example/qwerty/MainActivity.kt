package com.example.qwerty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn= findViewById<Button>(R.id.loginBtn)
        val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
//        findViewById<Button>(R.id.Email).setOnClickListener{EmailBtn()}
        
        loginBtn.setOnClickListener{
            if(editTextTextEmailAddress.text.toString().equals("Client") && editTextTextPassword.text.toString().equals("Client")){
                val toast = Toast.makeText(applicationContext, "Login Succeed!", Toast.LENGTH_LONG)
                toast.show()
                val intent0 = Intent(this, MenuActivty::class.java)
                startActivity(intent0)
            }
            else{
                val toast = Toast.makeText(applicationContext, "Password and/or Email is not correct. Please try again!", Toast.LENGTH_LONG)
                toast.show()
            }
    }

    }
}