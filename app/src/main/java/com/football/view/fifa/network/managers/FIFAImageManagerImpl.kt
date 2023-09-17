package com.football.view.fifa.network.managers

import com.football.view.fifa.network.services.FIFAImageService
import com.football.view.fifa.util.idmodule.NetworkProviderModule
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class FIFAImageManagerImpl @Inject constructor(
    @NetworkProviderModule.FifaImageRetrofit private val service: FIFAImageService
) : FIFAImageManager {

    override fun requestSpIdImage(spId: Int): Single<ResponseBody> =
        service.requestSpidImage(spId)
}

