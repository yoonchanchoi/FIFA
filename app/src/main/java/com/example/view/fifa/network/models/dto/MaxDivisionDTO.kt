package com.example.view.fifa.network.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MaxDivisionDTO(
    @SerializedName("matchType")
    @Expose
    val matchType: Int = 0,

    @SerializedName("division")
    @Expose
    val division: Int = 0,

    @SerializedName("achievementDate")
    @Expose
    val achievementDate: String = ""

)
