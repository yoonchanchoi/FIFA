package com.football.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.football.view.fifa.base.BaseViewModel
import com.football.view.fifa.network.managers.FIFAManager
import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.UserIdResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchSubViewModel @Inject constructor(
    private val fifaManager: FIFAManager
) : BaseViewModel() {

    private val _userDto = MutableLiveData<UserInfoResult>()
    val userDto: LiveData<UserInfoResult>
        get() = _userDto

//    private val _matchIdDTO = MutableLiveData<MatchIdDTO>()
//    val matchIdDTO: LiveData<MatchIdDTO>
//        get() = _matchIdDTO

//    private val _arrayMathId = MutableLiveData<ArrayList<String>>()
//    val arrayMathId: LiveData<ArrayList<String>>
//        get() = _arrayMathId

    private val _matchDTOList = MutableLiveData<ArrayList<MatchMetaDataResult>>()
    val matchDTOList: LiveData<ArrayList<MatchMetaDataResult>>
        get() = _matchDTOList

//    private val _userCheck = MutableLiveData<Boolean>()
//    val userCheck: LiveData<Boolean>
//    get() = _userCheck

    private val _recentSearchSaveCheck = MutableLiveData<Boolean>()
    val recentSearchSaveCheck: LiveData<Boolean>
    get() = _recentSearchSaveCheck

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String>
    get() = _nickname

//    private val _maxDivisionDTOList = MutableLiveData<ArrayList<MaxDivisionDTO>>()
//    val maxDivisionDTOList: LiveData<ArrayList<MaxDivisionDTO>>
//        get() = _maxDivisionDTOList

    private val _maxDivision = MutableLiveData<MaxDivisionResult>()
    val maxDivision: LiveData<MaxDivisionResult>
        get() = _maxDivision

    private val _userRank = MutableLiveData<String>()
    val userRank: LiveData<String>
        get() = _userRank

    fun requestUserId(nickname: String, recentSearchSaveCheck: Boolean){
        fifaManager.requestUserId(nickname)
            .subscribeOn(Schedulers.io())
            .subscribe({ userIdResult ->
                userIdResult?.let {
                    requestUserInfo(it.ouid, recentSearchSaveCheck)
                    Log.e("cyc", "유저 아이티 값---성공")
                }
            },{
                Log.e("cyc", "유저  아이디 값 ${it}")
            })
            .addTo(disposable)
    }
    private fun requestUserInfo(ouId: String, recentSearchSaveCheck: Boolean){
       fifaManager.requestUserInfo(ouId)
           .subscribeOn(Schedulers.io())
           .subscribe({ userInfo ->
                userInfo?.let {
                    _userDto.postValue(it)
                    _nickname.postValue(it.nickname)
                    requestMaxDivision(it.ouid)
                    _recentSearchSaveCheck.postValue(recentSearchSaveCheck)
                    requestMatchId(it.ouid)
                    Log.e("cyc", "유저 정보---성공")
                }
           },{
               Log.e("cyc", "유저 정보 실패 ${it}")
           })
           .addTo(disposable)
    }

    private fun requestMatchId(accessId: String){
        fifaManager.requestOfficialMatchId(accessId)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.e("cyc", "유저 경기---성공")
                requestMatchInfo(it)
            },{
                Log.e("cyc", "유저 경기 실패 : ${it}")
            })
            .addTo(disposable)
    }

    private fun requestMatchInfo(matchIds : ArrayList<String>) {
        Observable.fromIterable(matchIds)
            .subscribeOn(Schedulers.io())
            .concatMapSingle {
                fifaManager.requestMatchInfo(it)
            }
            .toList()
            .observeOn(Schedulers.io())
            .subscribe({
                _matchDTOList.postValue(it as ArrayList<MatchMetaDataResult>)
            },{
                Log.e("cyc", "유저 경기 정보 실패 : ${it}")
            })
            .addTo(disposable)
    }

    private fun requestMaxDivision(accessId: String) {
        fifaManager.requestMaxDivision(accessId)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.e("cyc", "유저의 최고 랭크---성공")
                checkDivision(it)
            },{
                Log.e("cyc", "유저의 최고 랭크 실패 : ${it}")
            })
            .addTo(disposable)
    }

    private fun checkDivision(maxDivisionDTOList: ArrayList<MaxDivisionResult>) {
        Observable.fromIterable(maxDivisionDTOList)
            .filter {
                it.matchType == 50
            }
            .map {
                when(it.division){
                    800 -> "슈퍼챔피언스"
                    900 -> "챔피언스"
                    1000 -> "슈퍼챌린지"
                    1100 -> "챌린지1"
                    1200 -> "챌린지2"
                    1300 -> "챌린지3"
                    2000 -> "월드클래스1"
                    2100 -> "월드클래스2"
                    2200 -> "월드클래스3"
                    2300 -> "프로1"
                    2400 -> "프로2"
                    2500 -> "프로3"
                    2600 -> "세미프로1"
                    2700 -> "세미프로2"
                    2800 -> "세미프로3"
                    2900 -> "유망주1"
                    3000 -> "유망주2"
                    3100 -> "유망주3"
                    else -> "공식경기 기록 없음"
                }
            }
            .subscribe({
                _userRank.postValue(it)
            },{
                Log.e("cyc", "유저의 최고 랭크 실패 : ${it}")
            })
            .addTo(disposable)
    }
}