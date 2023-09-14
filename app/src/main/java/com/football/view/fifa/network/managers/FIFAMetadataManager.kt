package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.*
import retrofit2.Call

interface FIFAMetadataManager {

    fun requestSpid(): Call<ArrayList<SpIdResult>>

    fun requestSpposition(): Call<ArrayList<SpPositionResult>>
}


