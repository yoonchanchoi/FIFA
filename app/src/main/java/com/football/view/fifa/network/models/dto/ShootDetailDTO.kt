package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShootDetailDTO(

    @SerializedName("goalTime")
    @Expose
    val goalTime: Int = 0,

    @SerializedName("x")
    @Expose
    val x: Double = 0.0,

    @SerializedName("y")
    @Expose
    val y: Double = 0.0,

    @SerializedName("type")
    @Expose
    val type: Int = 0,

    @SerializedName("result")
    @Expose
    val result: Int = 0,

    @SerializedName("spId")
    @Expose
    val spId: Int = 0,

    @SerializedName("spGrade")
    @Expose
    val spGrade: Int = 0,

    @SerializedName("spLevel")
    @Expose
    val spLevel: Int = 0,

    @SerializedName("spIdType")
    @Expose
    val spIdType: Boolean = false,

    @SerializedName("assist")
    @Expose
    val assist: Boolean = false,

    @SerializedName("assistSpId")
    @Expose
    val assistSpId: Int = 0,

    @SerializedName("assistX")
    @Expose
    val assistX: Double = 0.0,

    @SerializedName("assistY")
    @Expose
    val assistY: Double = 0.0,

    @SerializedName("hitPost")
    @Expose
    val hitPost: Boolean = false,

    @SerializedName("inPenalty")
    @Expose
    val inPenalty: Boolean = false

) : Serializable
