package com.example.view.fifa.network.managers

import com.example.view.fifa.network.services.FIFAService
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.network.services.FIFAImageService
import com.example.view.fifa.util.idmodule.NetworkProviderModule
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class FIFAImageManagerImpl @Inject constructor(@NetworkProviderModule.FifaImageRetrofit private val service: FIFAImageService) : FIFAImageManager {

    override fun requestSpidImage(spid: Int): Call<ResponseBody> =
        service.requestSpidImage(spid)
}

