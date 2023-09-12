package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StatusDTO(

    @SerializedName("shoot")
    @Expose
    val shoot: Int = 0,

    @SerializedName("effectiveShoot")
    @Expose
    val effectiveShoot: Int = 0,

    @SerializedName("assist")
    @Expose
    val assist: Int = 0,

    @SerializedName("goal")
    @Expose
    val goal: Int = 0,

    @SerializedName("dribble")
    @Expose
    val dribble: Int = 0,

    @SerializedName("intercept")
    @Expose
    val intercept: Int = 0,

    @SerializedName("defending")
    @Expose
    val defending: Int = 0,

    @SerializedName("passTry")
    @Expose
    val passTry: Int = 0,

    @SerializedName("passSuccess")
    @Expose
    val passSuccess: Int = 0,

    @SerializedName("dribbleTry")
    @Expose
    val dribbleTry: Int = 0,

    @SerializedName("dribbleSuccess")
    @Expose
    val dribbleSuccess: Int = 0,

    @SerializedName("ballPossesionTry")
    @Expose
    val ballPossesionTry: Int = 0,

    @SerializedName("ballPossesionSuccess")
    @Expose
    val ballPossesionSuccess: Int = 0,

    @SerializedName("aerialTry")
    @Expose
    val aerialTry: Int = 0,

    @SerializedName("aerialSuccess")
    @Expose
    val aerialSuccess: Int = 0,

    @SerializedName("blockTry")
    @Expose
    val blockTry: Int = 0,

    @SerializedName("block")
    @Expose
    val block: Int = 0,

    @SerializedName("tackleTry")
    @Expose
    val tackleTry: Int = 0,

    @SerializedName("tackle")
    @Expose
    val tackle: Int = 0,

    @SerializedName("yellowCards")
    @Expose
    val yellowCards: Int = 0,

    @SerializedName("redCards")
    @Expose
    val redCards: Int = 0,

    @SerializedName("spRating")
    @Expose
    val spRating: Float

) : Serializable
