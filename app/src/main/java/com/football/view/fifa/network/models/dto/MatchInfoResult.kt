package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchInfoResult(
    @SerializedName("accessId")
    @Expose
    val accessId: String = "",

    @SerializedName("nickname")
    @Expose
    val nickname: String = "",

    @SerializedName("matchDetail")
    @Expose
    val matchDetail: MatchDetailResult,

    @SerializedName("shoot")
    @Expose
    val shoot: ShootResult,

    @SerializedName("shootDetail")
    @Expose
    val shootDetail: ArrayList<ShootDetailResult>,

    @SerializedName("pass")
    @Expose
    val pass: PassResult,

    @SerializedName("defence")
    @Expose
    val defence: DefenceResult,

    @SerializedName("player")
    @Expose
    val player: ArrayList<PlayerResult>
) : Serializable
