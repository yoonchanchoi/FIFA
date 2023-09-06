package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchDetailDTO(

    @SerializedName("seasonId")
    @Expose
    val seasonId: Int = 0,

    @SerializedName("matchResult")
    @Expose
    val matchResult: String = "",

    @SerializedName("matchEndType")
    @Expose
    val matchEndType: Int = 0,

    @SerializedName("systemPause")
    @Expose
    val systemPause: Int = 0,

    @SerializedName("foul")
    @Expose
    val foul: Int = 0,

    @SerializedName("injury")
    @Expose
    val injury: Int = 0,

    @SerializedName("redCards")
    @Expose
    val redCards: Int = 0,

    @SerializedName("yellowCards")
    @Expose
    val yellowCards: Int = 0,

    @SerializedName("dribble")
    @Expose
    val dribble: Int = 0,

    @SerializedName("cornerKick")
    @Expose
    val cornerKick: Int = 0,

    @SerializedName("possession")
    @Expose
    val possession: Int = 0,

    @SerializedName("OffsideCount")
    @Expose
    val OffsideCount: Int = 0,

    @SerializedName("averageRating")
    @Expose
    val averageRating: Double = 0.0,

    @SerializedName("controller")
    @Expose
    val controller: String = ""
) : Serializable
