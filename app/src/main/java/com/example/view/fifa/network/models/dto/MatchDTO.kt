package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MatchDTO(
    @SerializedName("matchId")
    @Expose
    val matchId : String,

    @SerializedName("matchDate")
    @Expose
    val matchDate : String,

    @SerializedName("matchType")
    @Expose
    val matchType : Int,

    @SerializedName("matchInfo")
    @Expose
    val matchInfo : ArrayList<MatchInfoDTO>
)
