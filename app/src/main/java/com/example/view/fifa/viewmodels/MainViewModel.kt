package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _userdto = MutableLiveData<UserDTO>()
    val userdto: LiveData<UserDTO>
        get() = _userdto


    fun requestUserInfo(nickname: String) {
        repository.requestUserInfo(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items ->
                _userdto.postValue(items)
                Log.e("cyc", "items-->$items")
            }
    }

    fun requestMatchInfo(matchid: String) {

    }


    fun requestOfficialMatchId(accessid: String) {

    }


    fun requestMaxDivision(accessid: String) {

    }


}