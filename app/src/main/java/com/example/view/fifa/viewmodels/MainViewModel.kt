package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchIdDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _userdto = MutableLiveData<UserDTO>()
    val userdto: LiveData<UserDTO>
        get() = _userdto

    private val _arrayMathId = MutableLiveData<List<String>>()
    val arrayMathId: LiveData<List<String>>
        get() = _arrayMathId

    private val _matchDTOList = MutableLiveData<List<MatchDTO>>()
    val matchDTOList: LiveData<List<MatchDTO>>
        get() = _matchDTOList


//
//    private val _matchIdDTO = MutableLiveData<MatchIdDTO>()
//    val matchIdDTO: LiveData<MatchIdDTO>
//        get() = _matchIdDTO

    fun requestUserInfo(nickname: String) {
        repository.requestUserInfo(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { userDto ->
                requestMatchId(userDto.accessId)
                userDto
            }
            .subscribe ({ userDto ->
                _userdto.postValue(userDto)
                Log.e("cyc", "items-->$userDto")
            },{

            }).dispose()
    }

    fun requestMatchId(accessid: String){
        repository.requestOfficialMatchId(accessid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { matchIdDto ->
                matchIdDto.forEach{
                    requestMatchInfo(it)
                }
                matchIdDto
            }
            .subscribe({ matchIdDto ->
                _arrayMathId.postValue(matchIdDto)
                Log.e("cyc", "matchIdDto-->$matchIdDto")

            }).dispose()
    }

    fun requestMatchInfo(matchid: String) {
        repository.requestMatchInfo(matchid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val arrays: List<MatchDTO> = (_matchDTOList.value ?: emptyList()) + it
                return@map arrays
            }
            .subscribe({
                _matchDTOList.postValue(it)
            }).dispose()
    }



    fun requestMaxDivision(accessid: String) {

    }



}