package com.example.searchstudy.network.services

import com.example.searchstudy.network.models.response.*
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FIFAService {
//    @GET("nickname={nickname}")
//    fun requestTest(): Call<FIFAResponse>


    @GET("users")
    fun requestUserInfo(
        @Query("nickname") nicknamee: String
    ): Call<UserDTO>

    @GET("matches/{matchid}")
    fun requestMatchInfo(
        @Path("matchid") matchid: String
    ): Call<MatchDTO>

    @GET("users/{accessid}/matches")
    fun  requestOfficialMatchId(
        @Path("accessid") accessid : String,
        @Query("matchtype") matchtype : Int,
        @Query("offset") offset : Int,
        @Query("limit") limit : Int,
    ) : Call<List<String>>

    @GET("users/{accessid}/maxdivision")
    fun  requestMaxDivision(
        @Path("accessid") accessid: String
    ): Call<List<MaxDivisionDTO>>


}


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
