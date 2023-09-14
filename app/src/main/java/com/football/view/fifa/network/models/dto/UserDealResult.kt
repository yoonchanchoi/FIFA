package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDealResult(
    @SerializedName("tradeDate")
    @Expose
    val tradeDate: String = "",

    @SerializedName("saleSn")
    @Expose
    val saleSn: String = "",

    @SerializedName("spid")
    @Expose
    val spid: Int = 0,

    @SerializedName("grade")
    @Expose
    val grade: Int = 0,

    @SerializedName("value")
    @Expose
    val value: Int = 0

) : Serializable
