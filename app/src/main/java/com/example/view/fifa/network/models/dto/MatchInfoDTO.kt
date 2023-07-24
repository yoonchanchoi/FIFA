package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchInfoDTO(
    @SerializedName("accessId")
    @Expose
    val accessId :String,

    @SerializedName("nickname")
    @Expose
    val nickname :String,

    @SerializedName("matchDetail")
    @Expose
    val matchDetail :MatchDetailDTO,

    @SerializedName("shoot")
    @Expose
    val shoot :ShootDTO,

    @SerializedName("shootDetail")
    @Expose
    val shootDetail :ArrayList<ShootDetailDTO>,

    @SerializedName("pass")
    @Expose
    val pass :PassDTO,

    @SerializedName("defence")
    @Expose
    val defence :DefenceDTO,

    @SerializedName("player")
    @Expose
    val player :ArrayList<PlayerDTO>
): Serializable
