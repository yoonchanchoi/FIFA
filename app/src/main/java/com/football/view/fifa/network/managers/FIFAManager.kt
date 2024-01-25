package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.network.models.dto.UserIdResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import io.reactivex.Single
import retrofit2.Call

interface FIFAManager {

    //일반 api

    fun requestUserId(nickname: String): Single<UserIdResult>

    fun requestUserInfo(ouId: String): Single<UserInfoResult>

    fun requestOfficialMatchId(ouId : String): Single<ArrayList<String>>

    fun requestMatchInfo(matchId: String): Single<MatchMetaDataResult>

    fun requestMaxDivision(ouId: String): Single<ArrayList<MaxDivisionResult>>

    //메타데이터 api

    fun requestSpId(): Single<ArrayList<SpIdResult>>

    fun requestSpPosition(): Single<ArrayList<SpPositionResult>>

    fun requestSeasonId(): Single<ArrayList<SeasonIdResult>>
}


