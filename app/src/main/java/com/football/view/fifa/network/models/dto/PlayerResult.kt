package com.football.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class PlayerResult(

    @SerializedName("spId")
    @Expose
    open val spId: Int = 0,

    @SerializedName("spPosition")
    @Expose
    open val spPosition: Int = 0,

    @SerializedName("spGrade")
    @Expose
    open val spGrade: Int = 0,

    @SerializedName("status")
    @Expose
    open val status: StatusResult

) : Serializable
