package com.example.youtube.Models


import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
class Channel(var id: String? = "", var title: String? = "", var link: String? = "", var rank: Int? = 0, var reason: String? = ""):Serializable {
    override fun toString(): String {
        return "Name of Channel: $title \n\n"+
                "YT link: $link \n\n"+
                "Ranking: $rank \n\n"+
                "My reason for liking the channel: $reason\n\n"
    }
}