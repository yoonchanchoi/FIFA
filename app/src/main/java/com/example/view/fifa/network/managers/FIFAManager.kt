package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call
import retrofit2.Callback

interface FIFAManager {

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
    fun requestUserInfo(nickname: String): Call<UserDTO>


    fun requestMatchInfo(nickname: String): Call<MatchDTO>

    fun requestOfficialMatchId(accessid : String, matchtype : Int, offset : Int, limit : Int ): Call<ArrayList<String>>

    fun requestMaxDivision(nickname: String): Call<ArrayList<MaxDivisionDTO>>
}


