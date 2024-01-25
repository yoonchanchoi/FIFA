//package com.football.view.fifa.network.services
//
//import com.football.view.fifa.network.models.dto.SeasonIdResult
//import com.football.view.fifa.network.models.dto.SpIdResult
//import com.football.view.fifa.network.models.dto.SpPositionResult
//import io.reactivex.Single
//import retrofit2.http.GET
//
//interface FIFAMetadataService {
//
//    @GET("spid.json")
//    fun  requestSpId(): Single<ArrayList<SpIdResult>>
//
//    @GET("spposition.json")
//    fun  requestSpPosition(): Single<ArrayList<SpPositionResult>>
//
//    @GET("seasonid.json")
//    fun  requestSeasonId(): Single<ArrayList<SeasonIdResult>>
//}