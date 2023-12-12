package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import io.reactivex.Single

interface FIFAMetadataManager {

    fun requestSpId(): Single<ArrayList<SpIdResult>>

    fun requestSpPosition(): Single<ArrayList<SpPositionResult>>

    fun requestSeasonId(): Single<ArrayList<SeasonIdResult>>

}


