package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchDetailDTO(

    @SerializedName("seasonId")
    @Expose
    val seasonId : Int,

    @SerializedName("matchResult")
    @Expose
    val matchResult : String,

    @SerializedName("matchEndType")
    @Expose
    val matchEndType : Int,

    @SerializedName("systemPause")
    @Expose
    val systemPause : Int,

    @SerializedName("foul")
    @Expose
    val foul : Int,

    @SerializedName("injury")
    @Expose
    val injury : Int,

    @SerializedName("redCards")
    @Expose
    val redCards : Int,

    @SerializedName("yellowCards")
    @Expose
    val yellowCards : Int,

    @SerializedName("dribble")
    @Expose
    val dribble : Int,

    @SerializedName("cornerKick")
    @Expose
    val cornerKick : Int,

    @SerializedName("possession")
    @Expose
    val possession : Int,

    @SerializedName("OffsideCount")
    @Expose
    val OffsideCount : Int,

    @SerializedName("averageRating")
    @Expose
    val averageRating : Double,

    @SerializedName("controller")
    @Expose
    val controller : String
): Serializable
