package com.example.view.fifa.viewmodels

import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.managers.FIFAManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val fifaManager: FIFAManager
) : ViewModel() {




    fun requestUserInfo(nickname : String){

    }
}