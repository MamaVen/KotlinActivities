package com.example.uielements2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.uielements2.models.Album
import com.example.uielements2.models.AlbumSong
import com.example.uielements2.models.Song

class AlbumDetailsActivity : AppCompatActivity() {
    lateinit var album: Album
    lateinit var albumTitle: TextView
    lateinit var songsAlbumTableHandler: DatabaseHelper
    lateinit var albumSongItem: MutableList<AlbumSong>
    lateinit var adapter: ArrayAdapter<AlbumSong>
    lateinit var albumSongListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
        albumSongListView = findViewById<ListView>(R.id.album_songs)

        var albumItems: AlbumItem = intent.getSerializableExtra("data") as AlbumItem
        var viewImage = findViewById<ImageView>(R.id.icon_details)
        var viewText = findViewById<TextView>(R.id.icon_name)
        val album_id = intent.getIntExtra("album_id", 0)

        if(albumItems.icons == R.drawable.the_weeknd___house_of_balloons) {
            viewImage.setImageResource(albumItems.icons!!)
        songsAlbumTableHandler = DatabaseHelper(this)

            val songsQueueArray = arrayOf("House of Balloons/Glass table girls", "High for this", "What you need", "Loft Music", "The Morning")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        }
        else if(albumItems.icons == R.drawable.the_weeknd___after_hours){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "After Hours"

            val songsQueueArray = arrayOf("After Hours", "Blinding Lights", "Snowchild", "Alone again", "Too Late")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
        album = songsAlbumTableHandler.albumReadOne(album_id)

        albumTitle = findViewById(R.id.tv_album_title_details)

        albumTitle.setText(album.title)

        albumSongItem = songsAlbumTableHandler.albumSongRead()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumSongItem)
        albumSongListView.adapter = adapter

        registerForContextMenu(albumSongListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.album_details_remove_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position

        return when (item.itemId) {
            R.id.go_to_remove_album_song -> {
                val albumSong = albumSongItem[listPosition]
                if(songsAlbumTableHandler.albumSongDelete(albumSong)){
                    albumSongItem.removeAt(listPosition)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this,"Song deleted successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Song deletion failed", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
        else if(albumItems.icons == R.drawable.the_weeknd___beauty_behind_the_madness){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Behind the Madness"

            val songsQueueArray = arrayOf("Dark Times", "Shameless", "Real Life", "Can't feel my face", "As you are")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.album_details_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_a_song_album -> {
                startActivity(Intent(this, AlbumSongFormAdd::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
} 