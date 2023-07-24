package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShootDTO(
    @SerializedName("shootTotal")
    @Expose
    val shootTotal : Int,

    @SerializedName("effectiveShootTotal")
    @Expose
    val effectiveShootTotal : Int,

    @SerializedName("shootOutScore")
    @Expose
    val shootOutScore : Int,

    @SerializedName("goalTotal")
    @Expose
    val goalTotal : Int,

    @SerializedName("goalTotalDisplay")
    @Expose
    val goalTotalDisplay : Int,

    @SerializedName("ownGoal")
    @Expose
    val ownGoal : Int,

    @SerializedName("shootHeading")
    @Expose
    val shootHeading : Int,

    @SerializedName("goalHeading")
    @Expose
    val goalHeading : Int,

    @SerializedName("shootFreekick")
    @Expose
    val shootFreekick : Int,

    @SerializedName("goalFreekick")
    @Expose
    val goalFreekick : Int,

    @SerializedName("shootInPenalty")
    @Expose
    val shootInPenalty : Int,

    @SerializedName("goalInPenalty")
    @Expose
    val goalInPenalty : Int,

    @SerializedName("shootOutPenalty")
    @Expose
    val shootOutPenalty : Int,

    @SerializedName("goalOutPenalty")
    @Expose
    val goalOutPenalty : Int,

    @SerializedName("shootPenaltyKick")
    @Expose
    val shootPenaltyKick : Int,

    @SerializedName("goalPenaltyKick")
    @Expose
    val goalPenaltyKick : Int

): Serializable
