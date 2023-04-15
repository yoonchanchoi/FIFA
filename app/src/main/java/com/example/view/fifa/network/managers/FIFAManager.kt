package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call

//interface FIFAManager {
////    fun requestTest(): Call<FIFAResponse>
//
//    fun requestUserInfo(nickname: String): Single<UserDTO>
//
//    fun requestMatchInfo(nickname: String): Observable<MatchDTO>
//
//    fun requestOfficialMatchId(accessid : String, matchtype : Int, offset : Int, limit : Int ): Single<List<String>>
//
//    fun requestMaxDivision(nickname: String): Single<List<MaxDivisionDTO>>
//
//
//
//}