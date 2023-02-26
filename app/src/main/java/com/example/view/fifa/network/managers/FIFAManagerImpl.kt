package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.searchstudy.network.services.FIFAService
import retrofit2.Call
import javax.inject.Inject




class FIFAManagerImpl @Inject constructor(private val service: FIFAService) : FIFAManager {
    override fun requestTest(): Call<FIFAResponse> =
        service.requestTest()
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
