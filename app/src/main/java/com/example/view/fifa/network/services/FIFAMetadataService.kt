package com.example.view.fifa.network.services

import com.example.view.fifa.network.models.response.*
import com.example.view.fifa.network.models.dto.SpidDTO
import com.example.view.fifa.network.models.dto.SppositionDTO
import retrofit2.Call
import retrofit2.http.GET

interface FIFAMetadataService {

    @GET("spid.json")
    fun  requestSpid(): Call<ArrayList<SpidDTO>>

    @GET("spposition.json")
    fun  requestSpposition(): Call<ArrayList<SppositionDTO>>
}




