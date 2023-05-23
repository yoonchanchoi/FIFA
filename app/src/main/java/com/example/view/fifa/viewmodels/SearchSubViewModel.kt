
package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.network.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class SearchSubViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val disposables by lazy { CompositeDisposable() }

    private val _userdto = MutableLiveData<UserDTO>()
    val userdto: LiveData<UserDTO>
        get() = _userdto

    private val _arrayMathId = MutableLiveData<ArrayList<String>>()
    val arrayMathId: LiveData<ArrayList<String>>
        get() = _arrayMathId

    private val _matchDTOList = MutableLiveData<ArrayList<MatchDTO>>()
    val matchDTOList: LiveData<ArrayList<MatchDTO>>
        get() = _matchDTOList



    fun requestUserInfo(nickname: String) {
        repository.requestUserInfo(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { userDto ->
                requestMatchId(userDto.accessId)
                userDto
            }
            .subscribe({ userDto ->
                _userdto.postValue(userDto)
            }, {
            }).addToDisposables()
    }
    fun requestMatchId(accessid: String){
        repository.requestOfficialMatchId(accessid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map{ matchIdDto ->
                requestMatchInfo(matchIdDto)
                matchIdDto
            }
            .subscribe({ matchIdDto ->
                _arrayMathId.postValue(matchIdDto)
            },{
            }).addToDisposables()
    }

    private fun requestMatchInfo(matchIds: ArrayList<String>) {
            Observable.fromIterable(matchIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap {
                    repository.requestMatchInfo(it)
//                    requestMatchInfo(it)
                }
                .subscribe({
                    it.matchInfo
                })

//        matchIds.forEach {
//            Observable.fromArray(it)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .concatMap {
//                    Observable.just(repository.requestMatchInfo(it)
//                        .subscribe({
//                            _matchDTOList.postValue(it)
//                        },{
//                        }).addToDisposables())
//                }
//        }


    }

//    private fun requestMatchInfo(matchIds: String) {
//        repository.requestMatchInfo(matchIds)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
////            .map {
////                Log.e("cyc","it--->$it")
//////                Log.e("cyc","----뷰모델 matchDtoList---map--")
//////                val arrays: ArrayList<MatchDTO> = ((_matchDTOList.value ?: emptyList()) + it) as ArrayList<MatchDTO>
//////                return@map arrays
////                val list = _matchDTOList.value ?: emptyList()
////                val arrayList = ArrayList(list)
////                arrayList.add(it)
////                return@map arrayList
////            }
//            .concatMap { it ->
//                Log.e("cyc","it--->$it")
//                val list = _matchDTOList.value ?: emptyList()
//                val arrayList = ArrayList(list)
//                arrayList.add(it)
//                Observable.just(arrayList) // ArrayList를 Observable로 변환합니다.
//            }
//            .subscribe({
//                _matchDTOList.postValue(it)
//
//            },{
//            }).addToDisposables()
//    //        repository.requestMatchInfo(matchIds)
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
////                Log.e("cyc","----뷰모델 matchDtoList-----")
////                _matchDTOList.postValue(it)
////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---0---->${_matchDTOList.value!!.get(0)}")
////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---1---->${_matchDTOList.value!!.get(1)}")
////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---2---->${_matchDTOList.value!!.get(2)}")
////
////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---3---->${_matchDTOList.value!!.get(3)}")
////
////                Log.e("cyc","----뷰모델 matchDtoList---값!!!!---4---->${_matchDTOList.value!!.get(4)}")
////
////            },{
////                Log.d("cyc", it.message.toString())
////            }).addToDisposables()
//    }




    private fun Disposable.addToDisposables(): Disposable = addTo(disposables)


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