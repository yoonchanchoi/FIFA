package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShootDetailDTO(

    @SerializedName("goalTime")
    @Expose
    val goalTime: Int,

    @SerializedName("x")
    @Expose
    val x: Double,

    @SerializedName("y")
    @Expose
    val y: Double,

    @SerializedName("type")
    @Expose
    val type: Int,

    @SerializedName("result")
    @Expose
    val result: Int,

    @SerializedName("spId")
    @Expose
    val spId: Int,

    @SerializedName("spGrade")
    @Expose
    val spGrade: Int,

    @SerializedName("spLevel")
    @Expose
    val spLevel: Int,

    @SerializedName("spIdType")
    @Expose
    val spIdType: Boolean,

    @SerializedName("assist")
    @Expose
    val assist: Boolean,

    @SerializedName("assistSpId")
    @Expose
    val assistSpId: Int,

    @SerializedName("assistX")
    @Expose
    val assistX: Double,

    @SerializedName("assistY")
    @Expose
    val assistY: Double,

    @SerializedName("hitPost")
    @Expose
    val hitPost: Boolean,

    @SerializedName("inPenalty")
    @Expose
    val inPenalty: Boolean

)
