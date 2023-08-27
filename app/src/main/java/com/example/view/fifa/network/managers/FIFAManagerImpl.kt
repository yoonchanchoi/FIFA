package com.example.view.fifa.network.managers

import com.example.view.fifa.network.services.FIFAService
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.util.idmodule.NetworkProviderModule
import retrofit2.Call
import javax.inject.Inject

class FIFAManagerImpl @Inject constructor(@NetworkProviderModule.FifaRetrofit private val service: FIFAService) : FIFAManager {

    override fun requestUserInfo(nickname: String): Call<UserDTO> =
        service.requestUserInfo(nickname)

    override fun requestOfficialMatchId(accessid: String): Call<ArrayList<String>> =
        service.requestOfficialMatchId(accessid, matchType = 50, offset = 0, limit = 20)

    override fun requestMatchInfo(matchid: String): Call<MatchDTO> =
        service.requestMatchInfo(matchid)

    override fun requestMaxDivision(accessid: String): Call<ArrayList<MaxDivisionDTO>> =
        service.requestMaxDivision(accessid)

    ////-------------------------------------------------------------------------------------
////    //여기서 부터 rxjava잠금-1
//
////    override fun requestTest(): Call<FIFAResponse> =
////        service.requestTest()
//
//    override fun requestUserInfo(nickname : String): Single<UserDTO> =
//        service.requestUserInfo(nickname)
//
//    override fun requestMatchInfo(matchid: String): Observable<MatchDTO> =
//        service.requestMatchInfo(matchid)
//
//    override fun requestOfficialMatchId(accessid : String, matchtype : Int, offset : Int, limit : Int ): Single<List<String>> =
//        service.requestOfficialMatchId(accessid, matchtype, offset, limit)
//
//    override fun requestMaxDivision( accessid: String): Single<List<MaxDivisionDTO>> =
//        service.requestMaxDivision(accessid)
//
//
////    여기까지 rxjava잠금-1
////-------------------------------------------------------------------------------------

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
