package com.football.view.fifa.network.managers

import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import io.reactivex.Single
import retrofit2.Call

interface FIFAManager {

    fun requestUserInfo(nickname: String): Single<UserInfoResult>

    fun requestOfficialMatchId(accessId : String): Call<ArrayList<String>>

    fun requestMatchInfo(nickname: String): Call<MatchMetaDataResult>

    fun requestMaxDivision(nickname: String): Call<ArrayList<MaxDivisionResult>>
}


