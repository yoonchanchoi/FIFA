package com.example.view.fifa.network.services

import com.example.view.fifa.network.models.response.*
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FIFAImageService {

    @GET("playersAction/p{spid}.png")
    fun requestSpidImage(
        @Path("spid") spid: Int
    ): Call<ResponseBody>

}




