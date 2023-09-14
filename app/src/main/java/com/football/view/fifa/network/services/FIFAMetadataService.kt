package com.football.view.fifa.network.services

import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import retrofit2.Call
import retrofit2.http.GET

interface FIFAMetadataService {

    @GET("spid.json")
    fun  requestSpid(): Call<ArrayList<SpIdResult>>

    @GET("spposition.json")
    fun  requestSpposition(): Call<ArrayList<SpPositionResult>>
}




