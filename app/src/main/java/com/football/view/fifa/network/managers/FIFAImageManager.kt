package com.football.view.fifa.network.managers

import okhttp3.ResponseBody
import retrofit2.Call

interface FIFAImageManager {

    fun requestSpIdImage(spId: Int): Call<ResponseBody>
}


