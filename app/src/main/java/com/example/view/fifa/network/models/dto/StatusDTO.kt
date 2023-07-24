package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StatusDTO(

    @SerializedName("shoot")
    @Expose
    val shoot : Int,

    @SerializedName("effectiveShoot")
    @Expose
    val effectiveShoot : Int,

    @SerializedName("assist")
    @Expose
    val assist : Int,

    @SerializedName("goal")
    @Expose
    val goal : Int,

    @SerializedName("dribble")
    @Expose
    val dribble : Int,

    @SerializedName("intercept")
    @Expose
    val intercept : Int,

    @SerializedName("defending")
    @Expose
    val defending : Int,

    @SerializedName("passTry")
    @Expose
    val passTry : Int,

    @SerializedName("passSuccess")
    @Expose
    val passSuccess : Int,

    @SerializedName("dribbleTry")
    @Expose
    val dribbleTry : Int,

    @SerializedName("dribbleSuccess")
    @Expose
    val dribbleSuccess : Int,

    @SerializedName("ballPossesionTry")
    @Expose
    val ballPossesionTry : Int,

    @SerializedName("ballPossesionSuccess")
    @Expose
    val ballPossesionSuccess : Int,

    @SerializedName("aerialTry")
    @Expose
    val aerialTry : Int,

    @SerializedName("aerialSuccess")
    @Expose
    val aerialSuccess : Int,

    @SerializedName("blockTry")
    @Expose
    val blockTry : Int,

    @SerializedName("block")
    @Expose
    val block : Int,

    @SerializedName("tackleTry")
    @Expose
    val tackleTry : Int,

    @SerializedName("tackle")
    @Expose
    val tackle : Int,

    @SerializedName("yellowCards")
    @Expose
    val yellowCards : Int,

    @SerializedName("redCards")
    @Expose
    val redCards : Int,

    @SerializedName("spRating")
    @Expose
    val spRating : Float



): Serializable
