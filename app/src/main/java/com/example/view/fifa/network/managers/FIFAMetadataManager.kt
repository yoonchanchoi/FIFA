package com.example.view.fifa.network.managers

import com.example.view.fifa.network.models.dto.*
import retrofit2.Call

interface FIFAMetadataManager {

//    //test 개별데이터로 선수 이름값이 불러지는지
//    fun testRequestSpid(spid: Int): Call<SpidDTO>
    fun requestSpid(): Call<ArrayList<SpidDTO>>

    fun requestSpposition(): Call<ArrayList<SppositionDTO>>

}


