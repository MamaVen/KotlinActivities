package com.example.uielements2
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.R
import com.example.uielements2.models.Album
import com.example.uielements2.models.Song

class AlbumActivity : AppCompatActivity() {
    lateinit var albumItems: MutableList<Album>
    lateinit var albumTableHandler: DatabaseHelper
    lateinit var adapter: ArrayAdapter<Album>
    lateinit var gridView: GridView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        gridView = findViewById<GridView>(R.id.grid_view)

        albumTableHandler = DatabaseHelper(this)
        albumItems = albumTableHandler.albumRead()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumItems)


        gridView.adapter = adapter

        registerForContextMenu(gridView)

        gridView.setOnItemClickListener { parent, view, position, id ->
            val album_id = albumItems[position].id

            val intent = Intent(applicationContext, AlbumDetailsActivity::class.java)
            intent.putExtra("album_id", album_id)

            startActivity(intent)
        }
    }


        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.album_edit_menu, menu)
    }

        override fun onContextItemSelected(item: MenuItem): Boolean {


                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val listPosition = info.position

                return when (item.itemId) {
                    R.id.go_to_edit_album -> {
                        val album_id = albumItems[listPosition].id

                        imageView?.setImageResource(albumItem[position].icons!!)
                        val intent = Intent(applicationContext, AlbumFormEdit::class.java)
                        intent.putExtra("album_id", album_id)


                        startActivity(intent)
                        true
                    }
                    else -> super.onContextItemSelected(item)
                }
            }

            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                val inflater = menuInflater
                inflater.inflate(R.menu.album_menu, menu)
                return super.onCreateOptionsMenu(menu)
            }


                override fun onOptionsItemSelected(item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.go_to_add_album -> {
                            startActivity(Intent(this, AlbumFormAdd::class.java))
                            true
                        }
                        else -> super.onOptionsItemSelected(item)
                    }
                }


        }