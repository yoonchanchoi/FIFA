package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DefenceDTO(

    @SerializedName("blockTry")
    @Expose
    val blockTry: Int = 0,

    @SerializedName("blockSuccess")
    @Expose
    val blockSuccess: Int = 0,

    @SerializedName("tackleTry")
    @Expose
    val tackleTry: Int = 0,

    @SerializedName("tackleSuccess")
    @Expose
    val tackleSuccess: Int = 0

) : Serializable
