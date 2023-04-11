package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call

interface FIFAManager {
//    fun requestTest(): Call<FIFAResponse>

    fun requestUserInfo(nickname: String): Call<UserDTO>

    fun requestMatchInfo(nickname: String): Call<MatchDTO>

    fun requestOfficialMatchId(accessid : String, matchtype : Int, offset : Int, limit : Int ): Call<List<String>>

    fun requestMaxDivision(nickname: String): Call<List<MaxDivisionDTO>>



}