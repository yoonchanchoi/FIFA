package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.network.models.dto.UserIdResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import com.football.view.fifa.network.services.FIFAService
import com.football.view.fifa.util.idmodule.NetworkProviderModule
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

class FIFAManagerImpl @Inject constructor(
    private val service: FIFAService
) : FIFAManager {

    //일반 api

    override fun requestUserId(nickname: String): Single<UserIdResult> =
        service.requestUserId(nickname)

    override fun requestUserInfo(ouId: String): Single<UserInfoResult> =
        service.requestUserInfo(ouId)

    override fun requestOfficialMatchId(ouId: String): Single<ArrayList<String>> =
        service.requestOfficialMatchId(ouId, matchtype = 50, offset = 0, limit = 20, orderby = "desc")

    override fun requestMatchInfo(matchId: String): Single<MatchMetaDataResult> =
        service.requestMatchInfo(matchId)

    override fun requestMaxDivision(ouId: String) : Single<ArrayList<MaxDivisionResult>> =
        service.requestMaxDivision(ouId)

    //메타데이터 api

    override fun requestSpId(): Single<ArrayList<SpIdResult>> =
        service.requestSpId()

    override fun requestSpPosition(): Single<ArrayList<SpPositionResult>> =
        service.requestSpPosition()

    override fun requestSeasonId(): Single<ArrayList<SeasonIdResult>> =
        service.requestSeasonId()

}