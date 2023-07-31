package com.example.view.fifa.network.services

import com.example.view.fifa.network.models.response.*
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FIFAService {

    @GET("users")
    fun requestUserInfo(
        @Query("nickname") nicknamee: String
    ): Call<UserDTO>

    @GET("users/{accessId}/matches")
    fun  requestOfficialMatchId(
        @Path("accessId") accessId : String,
        @Query("matchType") matchType : Int,
        @Query("offset") offset : Int,
        @Query("limit") limit : Int,
    ) : Call<ArrayList<String>>

    @GET("matches/{matchId}")
    fun requestMatchInfo(
        @Path("matchId") matchId: String
    ): Call<MatchDTO>


    @GET("users/{accessId}/maxdivision")
    fun  requestMaxDivision(
        @Path("accessId") accessId: String
    ): Call<ArrayList<MaxDivisionDTO>>

}







//2196df1b745a7f7e06c234f8  고유식별자
//643232150db9d647d7149471  메치

//interface FIFAService {
//    @GET("nickname={nickname}")
//    fun requestTest(): Call<FIFAResponse>

////-------------------------------------------------------------------------------------
////    //여기서 부터 rxjava잠금-1
//
//    @GET("users")
//    fun requestUserInfo(
//        @Query("nickname") nicknamee: String
//    ): Single<UserDTO>
//
//    @GET("matches/{matchId}")
//    fun requestMatch(
//        @Path("matchId") matchId: String
//    ): Observable<MatchDTO>
//
//    @GET("users/{accessId}/matches")
//    fun  requestOfficialMatchId(
//        @Path("accessId") accessId : String,
//        @Query("matchType") matchType : Int,
//        @Query("offset") offset : Int,
//        @Query("limit") limit : Int,
//    ) : Single<ArrayList<String>>
//    //2196df1b745a7f7e06c234f8
//    //6457a174c4fea30485abc654
//
////    @GET("users/{accessId}/matches")
////    fun  requestOfficialMatchId(
////        @Path("accessId") accessId : String,
////        @Query("matchType") matchType : Int,
////        @Query("offset") offset : Int,
////        @Query("limit") limit : Int,
////    ) : Observable<ArrayList<String>>
//
//    @GET("users/{accessId}/maxdivision")
//    fun  requestMaxDivision(
//        @Path("accessId") accessId: String
//    ): Single<ArrayList<MaxDivisionDTO>>
//
////    여기까지 rxjava잠금-1
//
//
////-------------------------------------------------------------------------------------
//}


























//    @GET("/v1/search/blog.json")
//    fun requestBlog(
//        @Query(value = "query", encoded = true) query: String,
//        @Query(value = "display") display: Int,
//        @Query(value = "start") start: Int? = null,
//        @Query(value = "sort") sort: String? = null
//    ): Call<ResultSearchAll>
//
//
//    @GET("/v1/search/cafearticle.json")
//    fun requestCafe(
//        @Query(value = "query", encoded = true) query: String,
//        @Query(value = "display") display: Int? = 10,
//        @Query(value = "start") start: Int? = null,
//        @Query(value = "sort") sort: String? = null
//    ): Call<ResultSearchAll>
//
//    @GET("/v1/search/encyc.json")
//    fun requestDictionary(
//        @Query(value = "query", encoded = true) query: String,
//        @Query(value = "display") display: Int? = 10,
//        @Query(value = "start") start: Int? = null
//    ): Call<ResultSearchAll>
//
//    @GET("/v1/search/image")
//    fun requestImg(
//        @Query(value = "query", encoded = true) query: String,
//        @Query(value = "display") display: Int? = 20,
//        @Query(value = "start") start: Int? = null,
//        @Query(value = "sort") sort: String? = null,
//        @Query(value = "filter") filter: String? = null
//    ): Call<ResultSearchImg>
//
//
//    @GET("/v1/search/adult.json")
//    fun requestCheckAdultWord(
//        @Query(value = "query", encoded = true) query: String): Call<ResultCheckAdultWord>
//
//    @GET("/v1/search/errata.json")
//    fun requestCheckMissWord(
//        @Query(value = "query", encoded = true) query: String): Call<ResultMisspelledWord>
