package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfoResult(

    @SerializedName("ouid")
    @Expose
    val ouid: String = "",

    @SerializedName("nickname")
    @Expose
    val nickname: String = "",

    @SerializedName("level")
    @Expose
    val level: Int = 0

) : Serializable
