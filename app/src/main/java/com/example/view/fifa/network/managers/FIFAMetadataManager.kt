package com.example.view.fifa.network.managers

import com.example.view.fifa.network.models.dto.*
import retrofit2.Call

interface FIFAMetadataManager {

    fun requestSpid(): Call<ArrayList<SpidDTO>>

    fun requestSpposition(): Call<ArrayList<SppositionDTO>>

}


