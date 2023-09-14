package com.football.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.football.view.fifa.base.BaseViewModel
import com.football.view.fifa.network.managers.FIFAManager
import com.football.view.fifa.network.managers.FIFAMetadataManager
import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MaxDivisionResult
import com.football.view.fifa.network.models.dto.UserInfoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchSubViewModel @Inject constructor(
    private val fifaManager: FIFAManager,
    //Test
    private val fifaMetadataManager: FIFAMetadataManager
//rxjava(용)
//    private val repository: Repository
) : BaseViewModel() {

    private val _userdto = MutableLiveData<UserInfoResult>()
    val userdto: LiveData<UserInfoResult>
        get() = _userdto

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

    fun requestUserInfo(nickname: String, recentSearchSaveCheck: Boolean){
       fifaManager.requestUserInfo(nickname)
           .subscribeOn(Schedulers.io())
           .subscribe({ userInfo ->
                userInfo?.let {
                    _userdto.postValue(it)
                    _nickname.postValue(it.nickname)
                    requestMaxDivision(it.accessId)
                    _recentSearchSaveCheck.postValue(recentSearchSaveCheck)
                    requestMatchId(it.accessId)
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
                Log.e("cyc", "유저 경기---통신실패 (인터넷 연결의 문제, 예외발생)")
            })
            .addTo(disposable)
    }

    fun requestMatchInfo(matchIds : ArrayList<String>) {
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

    fun requestMaxDivision(accessId: String) {
        val result = fifaManager.requestMaxDivision(accessId)
        result.enqueue(object : Callback<ArrayList<MaxDivisionResult>>{
            override fun onResponse(call: Call<ArrayList<MaxDivisionResult>>, response: Response<ArrayList<MaxDivisionResult>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
//                        _maxDivisionDTOList.postValue(it)
                        _userRank.postValue(checkDivision(it))
                        Log.e("cyc", "유저의 최고 랭크---성공")
                    }
                }else{
//                    _userCheck.postValue(false)
                    Log.e("cyc", "유저의 최고 랭크---통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ArrayList<MaxDivisionResult>>, t: Throwable) {
//                _userCheck.postValue(false)
                Log.e("cyc", "유저의 최고 랭크---통신실패 (인터넷 연결의 문제, 예외발생)")
            }
        })
    }

    fun checkDivision(maxDivisionDTOList: ArrayList<MaxDivisionResult>): String {
        var userRank = "언랭"
        maxDivisionDTOList.forEach {
            if(it.matchType==50){
                //왜 maxDivision.postValue(it)을 하면 안되고
                // _maxDivision.value=it을 하면 되는가?
                //그 이유는 스레드 할당에 있다 LiveData의 postValue와 value의 차이를 찾아보자
                _maxDivision.value=it
            }
        }

        _maxDivision.value?.let {
            when(it.division){
                800 -> userRank = "슈퍼챔피언스"
                900 -> userRank = "챔피언스"
                1000 -> userRank = "슈퍼챌린지"
                1100 -> userRank = "챌린지1"
                1200 -> userRank = "챌린지2"
                1300 -> userRank = "챌린지3"
                2000 -> userRank = "월드클래스1"
                2100 -> userRank = "월드클래스2"
                2200 -> userRank = "월드클래스3"
                2300 -> userRank = "프로1"
                2400 -> userRank = "프로2"
                2500 -> userRank = "프로3"
                2600 -> userRank = "세미프로1"
                2700 -> userRank = "세미프로2"
                2800 -> userRank = "세미프로3"
                2900 -> userRank = "유망주1"
                3000 -> userRank = "유망주2"
                3100 -> userRank = "유망주3"
            }
        }
        return userRank
    }
}