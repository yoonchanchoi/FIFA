package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayerDTO(

    @SerializedName("spId")
    @Expose
    val spId: Int,

    @SerializedName("spPosition")
    @Expose
    val spPosition: Int,

    @SerializedName("spGrade")
    @Expose
    val spGrade: Int,

    @SerializedName("status")
    @Expose
    val status: StatusDTO

): Serializable
