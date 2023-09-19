package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpPositionResult(

    @SerializedName("spposition")
    @Expose
    val spposition: Int = 0,

    @SerializedName("desc")
    @Expose
    val desc: String = ""

) : Serializable
