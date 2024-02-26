package com.football.view.fifa.network.services

import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.network.models.dto.UserIdResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FIFAService {

    //일반 api

    @GET("fconline/v1/id")
    fun requestUserId(
        @Query("nickname") nickname: String
    ): Single<UserIdResult>

    @GET("fconline/v1/user/basic")
    fun requestUserInfo(
        @Query("ouid") ouId: String
    ): Single<UserInfoResult>

    @GET("fconline/v1/user/match")
    fun  requestOfficialMatchId(
        @Query("ouid") ouId : String,
        @Query("matchtype") matchtype : Int,
        @Query("offset") offset : Int,
        @Query("limit") limit : Int,
        @Query("orderby") orderby: String
    ) : Single<ArrayList<String>>

    @GET("fconline/v1/match-detail")
    fun requestMatchInfo(
        @Query("matchid") matchId: String
    ): Single<MatchMetaDataResult>

    @GET("fconline/v1/user/maxdivision")
    fun  requestMaxDivision(
        @Query("ouid") ouId: String
    ): Single<ArrayList<MaxDivisionResult>>

    //메타데이터 api

    @GET("static/fconline/meta/spid.json")
    fun  requestSpId(): Single<ArrayList<SpIdResult>>

    @GET("static/fconline/meta/spposition.json")
    fun  requestSpPosition(): Single<ArrayList<SpPositionResult>>

    @GET("static/fconline/meta/seasonid.json")
    fun  requestSeasonId(): Single<ArrayList<SeasonIdResult>>

}






//ffed31626d24758e232a092c41a83f11
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
