package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PassDTO(

    @SerializedName("passTry")
    @Expose
    val passTry: Int = 0,

    @SerializedName("passSuccess")
    @Expose
    val passSuccess: Int = 0,

    @SerializedName("shortPassTry")
    @Expose
    val shortPassTry: Int = 0,

    @SerializedName("shortPassSuccess")
    @Expose
    val shortPassSuccess: Int = 0,

    @SerializedName("longPassTry")
    @Expose
    val longPassTry: Int = 0,

    @SerializedName("longPassSuccess")
    @Expose
    val longPassSuccess: Int = 0,

    @SerializedName("bouncingLobPassTry")
    @Expose
    val bouncingLobPassTry: Int = 0,

    @SerializedName("bouncingLobPassSuccess")
    @Expose
    val bouncingLobPassSuccess: Int = 0,

    @SerializedName("drivenGroundPassTry")
    @Expose
    val drivenGroundPassTry: Int = 0,

    @SerializedName("drivenGroundPassSuccess")
    @Expose
    val drivenGroundPassSuccess: Int = 0,

    @SerializedName("throughPassTry")
    @Expose
    val throughPassTry: Int = 0,

    @SerializedName("throughPassSuccess")
    @Expose
    val throughPassSuccess: Int = 0,

    @SerializedName("lobbedThroughPassTry")
    @Expose
    val lobbedThroughPassTry: Int = 0,

    @SerializedName("lobbedThroughPassSuccess")
    @Expose
    val lobbedThroughPassSuccess: Int = 0

) : Serializable
