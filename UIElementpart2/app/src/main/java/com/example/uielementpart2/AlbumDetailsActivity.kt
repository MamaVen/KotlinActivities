package com.example.uielements2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.uielementpart2.R

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        var albumItems: AlbumItem = intent.getSerializableExtra("data") as AlbumItem
        var viewImage = findViewById<ImageView>(R.id.icon_details)
        var viewText = findViewById<TextView>(R.id.icon_name)

        if(albumItems.icons == R.drawable.blackpink___savage) {
            viewImage.setImageResource(albumItems.icons!!)

            val songsQueueArray = arrayOf("Savage", "How you like that", "Lovesick Girls", "Ddu Ddu", "Kill this love")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
        else if(albumItems.icons == R.drawable.blackpink___how_you_like_that){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "How you like that"

            val songsQueueArray = arrayOf("Icecream", "As if its your last", "Kick It", "Playing with fire", "Stay")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
        else if(albumItems.icons == R.drawable.blackpink___lovesick_girls){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Lovesick Girls"

            val songsQueueArray = arrayOf("Forever young", "Kiss and Makeup","Dont know what to do", "Can't feel my face", "As you are")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
    }
}