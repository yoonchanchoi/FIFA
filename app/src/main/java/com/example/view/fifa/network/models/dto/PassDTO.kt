package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PassDTO(

    @SerializedName("passTry")
    @Expose
    val passTry: Int,

    @SerializedName("passSuccess")
    @Expose
    val passSuccess: Int,

    @SerializedName("shortPassTry")
    @Expose
    val shortPassTry: Int,

    @SerializedName("shortPassSuccess")
    @Expose
    val shortPassSuccess: Int,

    @SerializedName("longPassTry")
    @Expose
    val longPassTry: Int,

    @SerializedName("longPassSuccess")
    @Expose
    val longPassSuccess: Int,

    @SerializedName("bouncingLobPassTry")
    @Expose
    val bouncingLobPassTry: Int,

    @SerializedName("bouncingLobPassSuccess")
    @Expose
    val bouncingLobPassSuccess: Int,

    @SerializedName("drivenGroundPassTry")
    @Expose
    val drivenGroundPassTry: Int,

    @SerializedName("drivenGroundPassSuccess")
    @Expose
    val drivenGroundPassSuccess: Int,

    @SerializedName("throughPassTry")
    @Expose
    val throughPassTry: Int,

    @SerializedName("throughPassSuccess")
    @Expose
    val throughPassSuccess: Int,

    @SerializedName("lobbedThroughPassTry")
    @Expose
    val lobbedThroughPassTry: Int,

    @SerializedName("lobbedThroughPassSuccess")
    @Expose
    val lobbedThroughPassSuccess: Int

): Serializable
