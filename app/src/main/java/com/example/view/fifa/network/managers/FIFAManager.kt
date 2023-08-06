package com.example.view.fifa.network.managers

import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.SpidDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call

interface FIFAManager {

    fun requestUserInfo(nickname: String): Call<UserDTO>

    fun requestOfficialMatchId(accessid : String): Call<ArrayList<String>>

    fun requestMatchInfo(nickname: String): Call<MatchDTO>

    fun requestMaxDivision(nickname: String): Call<ArrayList<MaxDivisionDTO>>



    ////-------------------------------------------------------------------------------------
////    //여기서 부터 rxjava잠금-1
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
////    여기까지 rxjava잠금-1
////-------------------------------------------------------------------------------------
}


