package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SppositionDTO(

    @SerializedName("spposition")
    @Expose
    val spposition: Int,

    @SerializedName("desc")
    @Expose
    val desc: String

) : Serializable
