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

    fun requestMatchInfo(matchid: String): Observable<MatchDTO> =
        service.requestMatchInfo(matchid)

    fun requestOfficialMatchId(accessid : String): Single<List<String>> =
        service.requestOfficialMatchId(accessid, matchtype = 50, offset = 0, limit = 20)

    fun requestMaxDivision( accessid: String): Single<List<MaxDivisionDTO>> =
        service.requestMaxDivision(accessid)
}