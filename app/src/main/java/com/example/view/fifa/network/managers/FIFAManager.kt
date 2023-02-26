package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import retrofit2.Call

interface FIFAManager {
    fun requestTest(): Call<FIFAResponse>

}