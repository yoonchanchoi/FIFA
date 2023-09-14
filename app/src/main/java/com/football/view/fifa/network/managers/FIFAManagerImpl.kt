package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import com.football.view.fifa.network.services.FIFAService
import com.football.view.fifa.util.idmodule.NetworkProviderModule
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class FIFAManagerImpl @Inject constructor(
    @NetworkProviderModule.FifaRetrofit private val service: FIFAService
) : FIFAManager {

    override fun requestUserInfo(nickname: String): Single<UserInfoResult> =
        service.requestUserInfo(nickname)

    override fun requestOfficialMatchId(accessId: String): Single<ArrayList<String>> =
        service.requestOfficialMatchId(accessId, matchType = 50, offset = 0, limit = 20)

    override fun requestMatchInfo(matchId: String): Single<MatchMetaDataResult> =
        service.requestMatchInfo(matchId)

    override fun requestMaxDivision(accessId: String): Call<ArrayList<MaxDivisionResult>> =
        service.requestMaxDivision(accessId)
}