package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDealDTO(
    @SerializedName("tradeDate")
    @Expose
    val tradeDate : String,

    @SerializedName("saleSn")
    @Expose
    val saleSn : String,

    @SerializedName("spid")
    @Expose
    val spid : Int,

    @SerializedName("grade")
    @Expose
    val grade : Int,

    @SerializedName("value")
    @Expose
    val value : Int

): Serializable
