package com.example.view.fifa.network.repository

import com.example.searchstudy.network.services.FIFAService
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class Repository @Inject constructor(private val service: FIFAService) {
//    override fun requestTest(): Call<FIFAResponse> =
//        service.requestTest()

    fun requestUserInfo(nickname : String): Single<UserDTO> =
        service.requestUserInfo(nickname)

    fun requestMatchInfo(matchId: String): Observable<MatchDTO> =
        service.requestMatchInfo(matchId)

//    fun requestOfficialMatchId(accessId : String): Observable<ArrayList<String>> =
//        service.requestOfficialMatchId(accessId, matchType = 50, offset = 0, limit = 20)

    fun requestOfficialMatchId(accessId : String): Single<ArrayList<String>> =
        service.requestOfficialMatchId(accessId, matchType = 50, offset = 0, limit = 20)

    fun requestMaxDivision(accessId: String): Single<ArrayList<MaxDivisionDTO>> =
        service.requestMaxDivision(accessId)
}