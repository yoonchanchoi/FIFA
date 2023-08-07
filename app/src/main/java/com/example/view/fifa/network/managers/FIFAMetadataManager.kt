package com.example.view.fifa.network.managers

import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.SpidDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call

interface FIFAMetadataManager {

    fun requestSpid(): Call<ArrayList<SpidDTO>>
}


