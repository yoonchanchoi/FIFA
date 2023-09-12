package com.football.view.fifa.network.managers

import okhttp3.ResponseBody
import retrofit2.Call

interface FIFAImageManager {

    fun requestSpidImage(spid: Int): Call<ResponseBody>
}


