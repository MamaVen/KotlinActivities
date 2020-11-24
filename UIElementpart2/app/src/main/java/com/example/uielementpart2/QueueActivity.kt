package com.example.uielements2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.uielementpart2.R

class QueueActivity : AppCompatActivity() {
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)
        val preferences = getSharedPreferences("sharedPrefs", 0)
        val songs = preferences.getString("songname", "")
        val songs2 = preferences.getString("songname2", "")
        val songs3 = preferences.getString("songname3", "")
        val songs4 = preferences.getString("songname4", "")
        val songs5 = preferences.getString("songname5", "")
        val songs6 = preferences.getString("songname6", "")
        val songs7 = preferences.getString("songname7", "")
        val songs8 = preferences.getString("songname8", "")
        val songs9 = preferences.getString("songname9", "")
        val songs10 = preferences.getString("songname10", "")
        val songs11 = preferences.getString("songname11", "")
        val songs12 = preferences.getString("songname12", "")
        val songs13 = preferences.getString("songname13", "")
        val songs14 = preferences.getString("songname14", "")
        val songs15 = preferences.getString("songname15", "")



        val songsQueueArray = mutableListOf(songs, songs2, songs3, songs4, songs5, songs6, songs7, songs8, songs9, songs10, songs11, songs12, songs13, songs14, songs15)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
        val songsList = findViewById<ListView>(R.id.queue_songs)
        songsList.adapter = adapter

        songsList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Do you want to remove this song ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                        dialog, which ->
                        val selectSong = songsQueueArray[position]
                        songsQueueArray.remove(selectSong)
                        adapter.notifyDataSetChanged()

                        val removedToast = Toast.makeText(applicationContext, "Song have been removed!", Toast.LENGTH_SHORT)
                        removedToast.show()
                        if(songsQueueArray.isNullOrEmpty()) {
                            val intent = Intent(this, QueueActivity::class.java)
                            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                notificationChannel = NotificationChannel(
                                        channelId, description, NotificationManager.IMPORTANCE_HIGH)
                                notificationChannel.enableLights(true)
                                notificationChannel.lightColor = Color.GREEN
                                notificationChannel.enableVibration(true)
                                notificationManager.createNotificationChannel(notificationChannel)

                                builder = Notification.Builder(this, channelId)
                                        .setContentTitle("Notfication for Queue")
                                        .setContentText("Song Queue have been emptied")
                                        .setSmallIcon(R.drawable.ic_launcher_background)
                                        .setContentIntent(pendingIntent)
                            } else {
                                builder = Notification.Builder(this)
                                        .setContentTitle("Notfication for Queue")
                                        .setContentText("Song Queue have been emptied")
                                        .setSmallIcon(R.drawable.ic_launcher_background)
                                        .setContentIntent(pendingIntent)
                            }
                            notificationManager.notify(1234, builder.build())
                        }

                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                        dialog, which ->
                        dialog.cancel()
                    })
            val alert = dialogBuilder.create()
            alert.setTitle("Notification Dialog")
            alert.show()
        }
    }
}