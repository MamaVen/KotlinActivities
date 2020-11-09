package com.example.qwerty

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import java.util.*


class MenuActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_activty)


        findViewById<Button>(R.id.search).setOnClickListener { WS() }
        findViewById<Button>(R.id.camera).setOnClickListener { Cam() }
        findViewById<Button>(R.id.video).setOnClickListener { Vid() }
        findViewById<Button>(R.id.album).setOnClickListener { Alb() }

    }

    private fun WS() {
        val intent4 = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.facebook.com/")
        startActivity(intent4)
    }

    private fun Cam() {
        val intent3 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(intent3)
    }

    private fun Alb() {
        Toast.makeText(this, "Action Failed.", Toast.LENGTH_SHORT).show()
        val intent1 = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("content://media/internal/images/media/")
    }

    private fun Vid() {
        Toast.makeText(this, "Action Failed.", Toast.LENGTH_SHORT).show()
        val intent2 = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (intent == null) {
            startActivity(intent2)
        }
    }
}







