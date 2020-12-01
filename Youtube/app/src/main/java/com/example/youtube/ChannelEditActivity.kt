package com.example.youtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.favoriteytchannel.handlers.ChannelHandler
import com.example.favoriteytchannel.models.Channel

class ChannelEditActivity : AppCompatActivity() {
    lateinit var btn_confirm: Button
    lateinit var et_title: EditText
    lateinit var et_link: EditText
    lateinit var et_rank: EditText
    lateinit var et_reason: EditText
    lateinit var channelHandler: ChannelHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_edit)

        var channelItems: Channel = intent.getSerializableExtra("data") as Channel

        et_title = findViewById(R.id.et_Title_edit)
        et_link = findViewById(R.id.et_YTLink_edit)
        et_rank = findViewById(R.id.et_Rank_edit)
        et_reason = findViewById(R.id.et_Reason_edit)
        btn_confirm = findViewById(R.id.btn_confirm_edit)
        channelHandler = ChannelHandler()

        et_title.setText(channelItems.title)
        et_link.setText(channelItems.link)
        et_rank.setText(channelItems.rank.toString())
        et_reason.setText(channelItems.reason)

        btn_confirm.setOnClickListener {
            val title = et_title.text.toString()
            val link = et_link.text.toString()
            val rank = et_rank.text.toString().toInt()
            val reason = et_reason.text.toString()

            val channel = Channel(id = channelItems.id, title = title, link = link, rank = rank, reason = reason)

            if(et_title.text.toString().isNotEmpty() && et_link.text.toString().isNotEmpty() && et_rank.text.toString().isNotEmpty() && et_reason.text.toString().isNotEmpty()){
                channelHandler.update(channel)
                Toast.makeText(applicationContext, "YT Channel edited successfully", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "Please enter the credentials", Toast.LENGTH_SHORT).show()
            }
        }

    }

}