package com.football.view.fifa.network.managers

import com.football.view.fifa.network.services.FIFAImageService
import com.football.view.fifa.util.idmodule.NetworkProviderModule
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class FIFAImageManagerImpl @Inject constructor(@NetworkProviderModule.FifaImageRetrofit private val service: FIFAImageService) : FIFAImageManager {

    override fun requestSpidImage(spid: Int): Call<ResponseBody> =
        service.requestSpidImage(spid)
}

