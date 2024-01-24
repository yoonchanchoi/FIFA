package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserIdResult(

    @SerializedName("ouid")
    @Expose
    val ouid: String = ""

) : Serializable

