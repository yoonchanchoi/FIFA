package com.example.view.fifa.network.services

import com.example.view.fifa.network.models.response.*
import com.example.view.fifa.network.models.dto.SpidDTO
import com.example.view.fifa.network.models.dto.SppositionDTO
import retrofit2.Call
import retrofit2.http.GET

interface FIFAMetadataService {

    @GET("spid.json")
    fun  requestSpid(): Call<ArrayList<SpidDTO>>

    @GET("spposition.json")
    fun  requestSpposition(): Call<ArrayList<SppositionDTO>>
}







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

