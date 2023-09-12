package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayerDTO(

    @SerializedName("spId")
    @Expose
    val spId: Int = 0,

    @SerializedName("spPosition")
    @Expose
    val spPosition: Int = 0,

    @SerializedName("spGrade")
    @Expose
    val spGrade: Int = 0,

    @SerializedName("status")
    @Expose
    val status: StatusDTO

) : Serializable
