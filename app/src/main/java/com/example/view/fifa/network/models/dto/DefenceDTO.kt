package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DefenceDTO(

    @SerializedName("blockTry")
    @Expose
    val blockTry :Int,

    @SerializedName("blockSuccess")
    @Expose
    val blockSuccess :Int,

    @SerializedName("tackleTry")
    @Expose
    val tackleTry :Int,

    @SerializedName("tackleSuccess")
    @Expose
    val tackleSuccess :Int

)
