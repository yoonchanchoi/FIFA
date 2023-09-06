package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpidDTO(
    @SerializedName("id")
    @Expose
    val id: Int = 0,

    @SerializedName("name")
    @Expose
    val name: String = ""

) : Serializable
