package com.example.view.fifa.network.managers

import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import okhttp3.ResponseBody
import retrofit2.Call

interface FIFAImageManager {

    fun requestSpidImage(spid: Int): Call<ResponseBody>
}


