package com.football.view.fifa.network.services

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FIFAImageService {

    @GET("playersAction/p{spid}.png")
    fun requestSpidImage(
        @Path("spid") spid: Int
    ): Single<ResponseBody>
}




