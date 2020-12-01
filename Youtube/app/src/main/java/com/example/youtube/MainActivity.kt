package com.example.youtube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.favoriteytchannel.handlers.ChannelHandler
import com.example.favoriteytchannel.models.Channel
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var channels: ArrayList<Channel>
        lateinit var channelListView: ListView
        lateinit var channelHandler: ChannelHandler
        lateinit var channel: Channel


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            channelListView = findViewById(R.id.listView_main)
            channels = ArrayList()
            channelHandler = ChannelHandler()

            registerForContextMenu(channelListView)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            super.onCreateContextMenu(menu, v, menuInfo)
            val inflater = menuInflater
            inflater.inflate(R.menu.channel_menu, menu)
        }

        override fun onContextItemSelected(item: MenuItem): Boolean {
            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
            return when(item.itemId) {
                R.id.go_to_edit -> {
                    channel = channels[info.position]
                    var intent = Intent(this,ChannelEditActivity::class.java)
                    intent.putExtra("data", channel)
                    startActivity(intent)
                    true
                }
                R.id.go_to_delete -> {
                    if(channelHandler.delete(channels[info.position])){
                        Toast.makeText(applicationContext, "YT Channel deleted successfully", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> super.onContextItemSelected(item)
            }
        }

        override fun onStart() {
            super.onStart()

            channelHandler.channelRef.orderByChild("rank").addValueEventListener(object: ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    channels.clear()
                    p0.children.forEach{
                        it -> val channel = it.getValue(Channel::class.java)
                        channels.add(channel!!)
                    }

                    val adapter = ArrayAdapter<Channel>(applicationContext, android.R.layout.simple_list_item_1, channels)
                    channelListView.adapter = adapter
                }
                override fun onCancelled(p0: DatabaseError) {

                }

            })
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.main_menu, menu)
            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.go_to_add -> {
                    startActivity(Intent(this, ChannelAddActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }