package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShootDTO(
    @SerializedName("shootTotal")
    @Expose
    val shootTotal: Int = 0,

    @SerializedName("effectiveShootTotal")
    @Expose
    val effectiveShootTotal: Int = 0,

    @SerializedName("shootOutScore")
    @Expose
    val shootOutScore: Int = 0,

    @SerializedName("goalTotal")
    @Expose
    val goalTotal: Int = 0,

    @SerializedName("goalTotalDisplay")
    @Expose
    val goalTotalDisplay: Int = 0,

    @SerializedName("ownGoal")
    @Expose
    val ownGoal: Int = 0,

    @SerializedName("shootHeading")
    @Expose
    val shootHeading: Int = 0,

    @SerializedName("goalHeading")
    @Expose
    val goalHeading: Int = 0,

    @SerializedName("shootFreekick")
    @Expose
    val shootFreekick: Int = 0,

    @SerializedName("goalFreekick")
    @Expose
    val goalFreekick: Int = 0,

    @SerializedName("shootInPenalty")
    @Expose
    val shootInPenalty: Int = 0,

    @SerializedName("goalInPenalty")
    @Expose
    val goalInPenalty: Int = 0,

    @SerializedName("shootOutPenalty")
    @Expose
    val shootOutPenalty: Int = 0,

    @SerializedName("goalOutPenalty")
    @Expose
    val goalOutPenalty: Int = 0,

    @SerializedName("shootPenaltyKick")
    @Expose
    val shootPenaltyKick: Int = 0,

    @SerializedName("goalPenaltyKick")
    @Expose
    val goalPenaltyKick: Int = 0

) : Serializable
