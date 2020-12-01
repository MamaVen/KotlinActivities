package com.example.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.favoriteytchannel.R
import com.example.favoriteytchannel.Handlers.ChannelHandler
import com.example.favoriteytchannel.Models.Channel
import com.example.youtube.Handlers.ChannelHandler
import com.example.youtube.Models.Channel

class ChannelAddActivity : AppCompatActivity() {
    lateinit var btn_confirm: Button
    lateinit var et_title: EditText
    lateinit var et_link: EditText
    lateinit var et_rank: EditText
    lateinit var et_reason: EditText
    lateinit var channelHandler: ChannelHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_add)

        et_title = findViewById(R.id.et_Title)
        et_link = findViewById(R.id.et_YTLink)
        et_rank = findViewById(R.id.et_Rank)
        et_reason = findViewById(R.id.et_Reason)
        btn_confirm = findViewById(R.id.btn_confirm_add)
        channelHandler = ChannelHandler()

        btn_confirm.setOnClickListener {
            val title = et_title.text.toString()
            val link = et_link.text.toString()
            val rank = et_rank.text.toString().toInt()
            val reason = et_reason.text.toString()

            val channel = Channel(title = title, link = link, rank =  rank, reason = reason)

            if(et_title.text.toString().isNotEmpty() && et_link.text.toString().isNotEmpty() && et_rank.text.toString().isNotEmpty() && et_reason.text.toString().isNotEmpty()){
                channelHandler.create(channel)
                Toast.makeText(applicationContext, "YT Channel added successfully", Toast.LENGTH_SHORT).show()
                clearFields()
            }
            else {
                Toast.makeText(applicationContext, "Please enter the credentials", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun clearFields(){
        et_title.text.clear()
        et_link.text.clear()
        et_rank.text.clear()
        et_reason.text.clear()
    }
}