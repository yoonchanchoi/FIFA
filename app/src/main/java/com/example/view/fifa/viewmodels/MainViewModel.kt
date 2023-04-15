package com.example.view.fifa.viewmodels

import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun requestUserInfo(nickname : String){

    }

    fun requestMatchInfo(matchid: String){

    }


    fun requestOfficialMatchId(accessid : String){

    }


    fun requestMaxDivision( accessid: String){

    }




}