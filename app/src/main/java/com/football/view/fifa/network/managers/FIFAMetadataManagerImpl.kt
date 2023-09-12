package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.SpidDTO
import com.football.view.fifa.network.models.dto.SppositionDTO
import com.football.view.fifa.network.services.FIFAMetadataService
import com.football.view.fifa.util.idmodule.NetworkProviderModule
import retrofit2.Call
import javax.inject.Inject

class FIFAMetadataManagerImpl @Inject constructor(@NetworkProviderModule.FifaMetadataRetrofit private val service: FIFAMetadataService) : FIFAMetadataManager {

    override fun requestSpid(): Call<ArrayList<SpidDTO>> =
        service.requestSpid()

    override fun requestSpposition(): Call<ArrayList<SppositionDTO>> =
        service.requestSpposition()
}

