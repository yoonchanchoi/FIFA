package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.network.services.FIFAMetadataService
import com.football.view.fifa.util.idmodule.NetworkProviderModule
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class FIFAMetadataManagerImpl @Inject constructor(
    @NetworkProviderModule.FifaMetadataRetrofit private val service: FIFAMetadataService
) : FIFAMetadataManager {

    override fun requestSpId(): Single<ArrayList<SpIdResult>> =
        service.requestSpId()

    override fun requestSpPosition(): Single<ArrayList<SpPositionResult>> =
        service.requestSpPosition()
}

