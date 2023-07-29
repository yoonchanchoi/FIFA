
package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.managers.FIFAManager
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchIdDTO
import com.example.view.fifa.network.models.dto.MaxDivisionDTO
import com.example.view.fifa.network.models.dto.UserDTO
//import com.example.view.fifa.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class SearchSubViewModel @Inject constructor(
    private val fifaManager: FIFAManager
//rxjava(용)
//    private val repository: Repository
) : ViewModel() {



    private val _userdto = MutableLiveData<UserDTO>()
    val userdto: LiveData<UserDTO>
        get() = _userdto

    private val _matchIdDTO = MutableLiveData<MatchIdDTO>()
    val matchIdDTO: LiveData<MatchIdDTO>
        get() = _matchIdDTO

    private val _arrayMathId = MutableLiveData<ArrayList<String>>()
    val arrayMathId: LiveData<ArrayList<String>>
        get() = _arrayMathId

    private val _matchDTOList = MutableLiveData<ArrayList<MatchDTO>>()
    val matchDTOList: LiveData<ArrayList<MatchDTO>>
        get() = _matchDTOList

    private val _userCheck = MutableLiveData<Boolean>()
    val userCheck: LiveData<Boolean>
    get() = _userCheck

    private val _recentSearchSaveCheck = MutableLiveData<Boolean>()
    val recentSearchSaveCheck: LiveData<Boolean>
    get() = _recentSearchSaveCheck

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String>
    get() = _nickname

    private val _maxDivisionDTOList = MutableLiveData<ArrayList<MaxDivisionDTO>>()
    val maxDivisionDTOList: LiveData<ArrayList<MaxDivisionDTO>>
        get() = _maxDivisionDTOList

    private val _maxDivision = MutableLiveData<MaxDivisionDTO>()
    val maxDivision: LiveData<MaxDivisionDTO>
        get() = _maxDivision

    private val _userRank = MutableLiveData<String>()
    val userRank: LiveData<String>
        get() = _userRank




    fun requestUserInfo(nickname: String,recentSearchSaveCheck: Boolean){
       val result = fifaManager.requestUserInfo(nickname)
        result.enqueue(object : Callback<UserDTO>{
            override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        _userdto.postValue(it)
                        _userCheck.postValue(true)
                        _nickname.postValue(nickname)
                        requestMaxDivision(it.accessId)
                        _recentSearchSaveCheck.postValue(recentSearchSaveCheck)
                        requestMatchId(it.accessId)
                        Log.e("cyc", "성공")
                    }
                }else{
                    _userCheck.postValue(false)
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }

            }

            override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                _userCheck.postValue(false)
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")

            }
        })
    }

    fun requestMatchId(accessId: String){
        val result = fifaManager.requestOfficialMatchId(accessId)
        result.enqueue(object : Callback<ArrayList<String>>{
            override fun onResponse(
                call: Call<ArrayList<String>>,
                response: Response<ArrayList<String>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let{
                        _arrayMathId.postValue(it)
                        requestMatchInfo(it)
                    }

                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }

        })
    }

    fun requestMatchInfo(matchIds : ArrayList<String>) {
        var tmpMatchDToList = ArrayList<MatchDTO>()
        matchIds.forEach {
            val result = fifaManager.requestMatchInfo(it)
            result.enqueue(object : Callback<MatchDTO>{
                override fun onResponse(call: Call<MatchDTO>, response: Response<MatchDTO>) {
                    if(response.isSuccessful){
                        response.body()?.let { MatchDTO->
                            tmpMatchDToList.add(MatchDTO)
                            if(matchIds.indexOf(it) == matchIds.size-1){
                                _matchDTOList.postValue(tmpMatchDToList)
                            }
                        }
                    }else{
                        Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                    }
                }
                override fun onFailure(call: Call<MatchDTO>, t: Throwable) {
                    Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
                }
            })
            // 박에서 받는것을 한다.
            // synchronized로 처리할 될 내부의 repsonse를 뺄수가 없다..일단 다시 해본다 오늘 시도했지만 터졋다..
        }
    }

    fun requestMaxDivision(accessId: String) {
        val result = fifaManager.requestMaxDivision(accessId)
        result.enqueue(object : Callback<ArrayList<MaxDivisionDTO>>{
            override fun onResponse(call: Call<ArrayList<MaxDivisionDTO>>, response: Response<ArrayList<MaxDivisionDTO>>) {
                if(response.isSuccessful) {
                    response.body()?.let {
                        _maxDivisionDTOList.postValue(it)
                        Log.e("cyc","공식 경기 랭크 값들--->${it}")
                        _userRank.postValue(checkDivision(it))
                        Log.e("cyc", "성공")
                    }
                }else{
                    _userCheck.postValue(false)
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }

            }

            override fun onFailure(call: Call<ArrayList<MaxDivisionDTO>>, t: Throwable) {
                _userCheck.postValue(false)
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")

            }
        })
    }

    fun checkDivision(maxDivisionDTOList: ArrayList<MaxDivisionDTO>):String{
        var userRank = "언랭"
        maxDivisionDTOList.forEach {
            if(it.matchType==50){
                Log.e("cyc","여기를 타는가?")
                Log.e("cyc","여기를 타는가? 의 it--->$it")
                Log.e("cyc","여기를 타는가? 의 it.division--->${it.division}")

                //왜 maxDivision.postValue(it)을 하면 안되고
                // _maxDivision.value=it을 하면 되는가?
                _maxDivision.value=it
            }
        }
        Log.e("cyc","_maxDivision.value--->${_maxDivision.value}")

        _maxDivision.value?.let {
            when(it.division){
                800->{userRank="슈퍼챔피언스"}
                900->{userRank="챔피언스"}
                1000->{userRank="슈퍼챌린지"}
                1100->{userRank="챌린지1"}
                1200->{userRank="챌린지2"}
                1300->{userRank="챌린지3"}
                2000->{userRank="월드클래스1"}
                2100->{userRank="월드클래스2"}
                2200->{userRank="월드클래스3"}
                2300->{userRank="프로1"}
                2400->{userRank="프로2"}
                2500->{userRank="프로3"}
                2600->{userRank="세미프로1"}
                2700->{userRank="세미프로2"}
                2800->{userRank="세미프로3"}
                2900->{userRank="유망주1"}
                3000->{userRank="유망주2"}
                3100->{userRank="유망주3"}
            }
        }
        Log.e("cyc","유저 최고 랭크--->${userRank}")
        return userRank
    }







//-------------------------------------------------------------------------------------
//
//    private val disposables by lazy { CompositeDisposable() }
//
//    private val _userdto = MutableLiveData<UserDTO>()
//    val userdto: LiveData<UserDTO>
//        get() = _userdto
//
//    private val _matchIdDTO = MutableLiveData<MatchIdDTO>()
//    val matchIdDTO: LiveData<MatchIdDTO>
//        get() = _matchIdDTO
//
//    private val _arrayMathId = MutableLiveData<ArrayList<String>>()
//    val arrayMathId: LiveData<ArrayList<String>>
//        get() = _arrayMathId
//
//    private val _matchDTOList = MutableLiveData<ArrayList<MatchDTO>>()
//    val matchDTOList: LiveData<ArrayList<MatchDTO>>
//        get() = _matchDTOList
//
//    //여기서 부터 rxjava잠금-1
//
//    private val _matchDTOList = MutableLiveData<List<MatchDTO>>()
//    val matchDTOList: LiveData<List<MatchDTO>>
//        get() = _matchDTOList
//
//
//
//
//
//
//    fun requestUserInfo(nickname: String) {
//        repository.requestUserInfo(nickname)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { userDto ->
//                requestMatchId(userDto.accessId)
//                userDto
//            }
//            .subscribe({ userDto ->
//                _userdto.postValue(userDto)
//            }, {
//            }).addToDisposables()
//    }
//    fun requestMatchId(accessid: String){
//        repository.requestOfficialMatchId(accessid)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map{ matchIdDto ->
//                requestMatchInfo(matchIdDto)
//                matchIdDto
//            }
//            .subscribe({ matchIdDto ->
//                _matchIdDTO.value?.let {
//                    it.mathId=matchIdDto
//                }
//            },{
//            }).addToDisposables()
//    }
//
//    private fun requestMatchInfo(matchIds: ArrayList<String>) {
//            Observable.fromIterable(matchIds)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .concatMap {
//                    repository.requestMatch(it)
//                }
//                .toList()
//                .subscribe(
//                    {
//                        _matchDTOList.postValue(it)
//                     //여기서 받은 MatchDTO를 모아서 라이브데이터에 넣어야되는데 어케해야되나...
////                    _matchDTOList.value
//                },{
//                    Log.e("cyc","_matchDToList에러")
//                    }
//                )
//
////        matchIds.forEach {
////            Observable.fromArray(it)
////                .subscribeOn(Schedulers.io())
////                .observeOn(A
//    //                ndroidSchedulers.mainThread())
////                .concatMap {
////                    Observable.just(repository.requestMatchInfo(it)
////                        .subscribe({
////                            _matchDTOList.postValue(it)
////                        },{
////                        }).addToDisposables())
////                }
////        }
//
//
//    }
//
////    private fun requestMatchInfo(matchIds: String) {
////        repository.requestMatchInfo(matchIds)
////            .subscribeOn(Schedulers.io())
////            .observeOn(AndroidSchedulers.mainThread())
//////            .map {
//////                Log.e("cyc","it--->$it")
////////                Log.e("cyc","----뷰모델 matchDtoList---map--")
////////                val arrays: ArrayList<MatchDTO> = ((_matchDTOList.value ?: emptyList()) + it) as ArrayList<MatchDTO>
////////                return@map arrays
//////                val list = _matchDTOList.value ?: emptyList()
//////                val arrayList = ArrayList(list)
//////                arrayList.add(it)
//////                return@map arrayList
//////            }
////            .concatMap { it ->
////                Log.e("cyc","it--->$it")
////                val list = _matchDTOList.value ?: emptyList()
////                val arrayList = ArrayList(list)
////                arrayList.add(it)
////                Observable.just(arrayList) // ArrayList를 Observable로 변환합니다.
////            }
////            .subscribe({
////                _matchDTOList.postValue(it)
////
////            },{
////            }).addToDisposables()
////    //        repository.requestMatchInfo(matchIds)
//////            .subscribeOn(Schedulers.io())
//////            .observeOn(AndroidSchedulers.mainThread())
////////            .map {
////////                Log.e("cyc","it--->$it")
//////////                Log.e("cyc","----뷰모델 matchDtoList---map--")
//////////                val arrays: ArrayList<MatchDTO> = ((_matchDTOList.value ?: emptyList()) + it) as ArrayList<MatchDTO>
//////////                return@map arrays
////////                val list = _matchDTOList.value ?: emptyList()
////////                val arrayList = ArrayList(list)
////////                arrayList.add(it)
////////                return@map arrayList
////////            }
//////            .concatMap { it ->
//////                Log.e("cyc","it--->$it")
//////                val list = _matchDTOList.value ?: emptyList()
//////                val arrayList = ArrayList(list)
//////                arrayList.add(it)
//////                Observable.just(arrayList) // ArrayList를 Observable로 변환합니다.
//////            }
//////            .subscribe({
//////                Log.e("cyc","----뷰모델 matchDtoList-----")
//////                _matchDTOList.postValue(it)
//////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---0---->${_matchDTOList.value!!.get(0)}")
//////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---1---->${_matchDTOList.value!!.get(1)}")
//////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---2---->${_matchDTOList.value!!.get(2)}")
//////
//////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---3---->${_matchDTOList.value!!.get(3)}")
//////
//////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---4---->${_matchDTOList.value!!.get(4)}")
//////
//////            },{
//////                Log.d("cyc", it.message.toString())
//////            }).addToDisposables()
////    }
//
//
//
//
//    private fun Disposable.addToDisposables(): Disposable = addTo(disposables)
//
////    여기까지 rxjava잠금-1


//-------------------------------------------------------------------------------------

}

//
//
//
//package com.example.view.fifa.viewmodels
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.view.fifa.network.models.dto.MatchDTO
//import com.example.view.fifa.network.models.dto.MatchIdDTO
//import com.example.view.fifa.network.models.dto.UserDTO
//import com.example.view.fifa.network.repository.Repository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.disposables.Disposable
//import io.reactivex.rxkotlin.addTo
//import io.reactivex.schedulers.Schedulers
//import javax.inject.Inject
//
//@HiltViewModel
//class SearchSubViewModel @Inject constructor(
//    private val repository: Repository
//) : ViewModel() {
//
//    private val disposables by lazy { CompositeDisposable() }
//
//    private val _userdto = MutableLiveData<UserDTO>()
//    val userdto: LiveData<UserDTO>
//        get() = _userdto
//
//    private val _arrayMathId = MutableLiveData<ArrayList<String>>()
//    val arrayMathId: LiveData<ArrayList<String>>
//        get() = _arrayMathId
//
//    private val _matchDTOList = MutableLiveData<ArrayList<MatchDTO>>()
//    val matchDTOList: LiveData<ArrayList<MatchDTO>>
//        get() = _matchDTOList
//
//
//
//    fun requestUserInfo(nickname: String) {
//        Log.e("cyc","검색 클릭시뷰모델")
//        repository.requestUserInfo(nickname)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { userDto ->
//                Log.e("cyc","검색클릭 뷰모델 맵")
//                requestMatchId(userDto.accessId)
//                userDto
//            }
//            .subscribe ({ userDto ->
//                _userdto.postValue(userDto)
//                Log.e("cyc","검색 성공")
//                Log.e("cyc", "items-->$userDto")
//            },{
//                Log.d("cyc", it.message.toString())
//            }).addToDisposables()
//    }
//
//    fun requestMatchId(accessid: String){
//        repository.requestOfficialMatchId(accessid)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .flatMapIterable { it }
//            .flatMap { matchIdDto ->
//                requestMatchInfo(matchIdDto)
//            }
//            .toList()
//            .subscribe({ matchDTOList ->
//                _matchDTOList.postValue(ArrayList(matchDTOList))
//                Log.e("cyc", "matchDTOList-->$matchDTOList")
//            },{
//                Log.d("cyc", it.message.toString())
//            }).addToDisposables()
//    }
//
//    private fun requestMatchInfo(matchId: String): Disposable {
//        return repository.requestMatchInfo(matchId)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnError { error ->
//                Log.e("cyc", error.message.toString())
//            }
//            .onErrorReturnItem(MatchDTO())
//            .subscribe({ matchDTO ->
//                _matchDTOList.value?.add(matchDTO)
//                _matchDTOList.postValue(_matchDTOList.value)
//            },{
//                Log.d("cyc", it.message.toString())
//            }).addToDisposables()
//    }
//
//    fun requestMaxDivision(accessid: String) {
//
//    }
//
//    private fun Disposable.addToDisposables(): Disposable = addTo(disposables)
//}