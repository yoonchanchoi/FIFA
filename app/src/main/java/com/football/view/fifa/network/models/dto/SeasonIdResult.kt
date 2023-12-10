package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SeasonIdResult(
    @SerializedName("seasonId")
    @Expose
    val seasonId: Int = 0,

    @SerializedName("className")
    @Expose
    val className: String = "",

    @SerializedName("seasonImg")
    @Expose
    val seasonImg: String = ""

) : Serializable