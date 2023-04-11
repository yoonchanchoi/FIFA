package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.searchstudy.network.services.FIFAService
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call
import javax.inject.Inject




class FIFAManagerImpl @Inject constructor(private val service: FIFAService) : FIFAManager {
//    override fun requestTest(): Call<FIFAResponse> =
//        service.requestTest()

    override fun requestUserInfo(nickname : String): Call<UserDTO> =
        service.requestUserInfo(nickname)

    override fun requestMatchInfo(matchid: String): Call<MatchDTO> =
        service.requestMatchInfo(matchid)

    override fun requestOfficialMatchId(accessid : String, matchtype : Int, offset : Int, limit : Int ): Call<List<String>> =
        service.requestOfficialMatchId(accessid, matchtype, offset, limit)

    override fun requestMaxDivision( accessid: String): Call<List<MaxDivisionDTO>> =
        service.requestMaxDivision(accessid)
}











//
//class FIFAManagerImpl @Inject constructor(private val service: FIFAService) : FIFAManager {
//    override fun requestBlog(query: String, display: Int, start: Int): Call<ResultSearchAll> =
//        service.requestBlog(query,display = display, start = start)
//
//    override fun requestCafe(query: String, start: Int): Call<ResultSearchAll> =
//        service.requestCafe(query, start = start)
//
//    override fun requestDictionary(query: String, start: Int): Call<ResultSearchAll> =
//        service.requestDictionary(query, start = start)
//
//    override fun requestImg(query: String, start: Int): Call<ResultSearchImg> =
//        service.requestImg(query, start = start)
//
//    override fun requestCheckAdultWord(query: String): Call<ResultCheckAdultWord> =
//        service.requestCheckAdultWord(query)
//
//    override fun requestCheckMissWord(query: String): Call<ResultMisspelledWord> =
//        service.requestCheckMissWord(query)
//
//
//}
//
