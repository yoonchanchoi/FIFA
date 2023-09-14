package com.football.view.fifa.network.services

import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FIFAService {

    @GET("users")
    fun requestUserInfo(
        @Query("nickname") nicknamee: String
    ): Single<UserInfoResult>

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
    ): Call<MatchMetaDataResult>


    @GET("users/{accessId}/maxdivision")
    fun  requestMaxDivision(
        @Path("accessId") accessId: String
    ): Call<ArrayList<MaxDivisionResult>>

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
