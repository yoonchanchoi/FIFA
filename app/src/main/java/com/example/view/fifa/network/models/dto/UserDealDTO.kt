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
//2196df1b745a7f7e06c234f8  고유식별자
//643232150db9d647d7149471  메치