package com.example.view.fifa.network.managers


import com.example.view.fifa.network.models.dto.SpidDTO
import com.example.view.fifa.network.models.dto.SppositionDTO
import com.example.view.fifa.network.services.FIFAMetadataService
import com.example.view.fifa.util.idmodule.NetworkProviderModule
import retrofit2.Call
import javax.inject.Inject


class FIFAMetadataManagerImpl @Inject constructor(@NetworkProviderModule.FifaMetadataRetrofit private val service: FIFAMetadataService) : FIFAMetadataManager {

    override fun requestSpid(): Call<ArrayList<SpidDTO>> =
        service.requestSpid()

    override fun requestSpposition(): Call<ArrayList<SppositionDTO>> =
        service.requestSpposition()

}

