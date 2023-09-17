package com.football.view.fifa.viewmodels

import androidx.lifecycle.ViewModel
import com.football.view.fifa.network.models.dto.*
import com.football.view.fifa.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val pref: Pref,

) : ViewModel() {

    var _spidDTOList = ArrayList<SpidDTO>()
    var _sppositionDTOList = ArrayList<SppositionDTO>()

    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerDTO>()
    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerDTO>()

    fun getPreData(){
        _spidDTOList = pref.getAllSpidList() as ArrayList<SpidDTO>
        _sppositionDTOList = pref.getAllSppositionList() as ArrayList<SppositionDTO>
    }

    fun setPlayer(matchDTO: MatchDTO) {
        matchDTO.matchInfo[0].player.forEach {
//            Log.e("cyc", "아오아오아오아오--->${it.spId}")
            filtedMatchMyPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
//            if(matchDTO.matchInfo[0].player.indexOf(it)==matchDTO.matchInfo[0].player.size-1){
//                _matchMyPlayerDTOList.postValue(_tempMatchMyPlayerDTOList)
//            }
        }

        matchDTO.matchInfo[1].player.forEach {
            filtedMatchOpponentPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
//            if(matchDTO.matchInfo[1].player.indexOf(it)==matchDTO.matchInfo[1].player.size-1){
//                _matchOpponentPlayerDTOList.postValue(_tempMatchOpponentPlayerDTOList)
//            }
        }
    }

    private fun pickUpPlayer(id: Int, position: Int): MatchPlayerDTO {
        var name = ""
        var desc = ""

        _spidDTOList?.let { spidDTOS ->
            spidDTOS.forEach {
                if (it.id == id) {
                    name = it.name

                }
            }
        }

        _sppositionDTOList?.let { sppositionDTOS ->
            sppositionDTOS.forEach {
                if (it.spposition == position) {
                    desc = it.desc
                }
            }
        }
        return MatchPlayerDTO(name, desc)
    }




}















//Rxjava 로직
//package com.football.view.fifa.viewmodels
//
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import com.football.view.fifa.base.BaseViewModel
//import com.football.view.fifa.network.managers.FIFAImageManager
//import com.football.view.fifa.network.models.dto.MatchMetaDataResult
//import com.football.view.fifa.network.models.dto.MatchPlayerResult
//import com.football.view.fifa.network.models.dto.SpIdResult
//import com.football.view.fifa.network.models.dto.SpPositionResult
//import com.football.view.fifa.util.Pref
//import dagger.hilt.android.lifecycle.HiltViewModel
//import io.reactivex.Observable
//import io.reactivex.Single
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.rxkotlin.Observables
//import io.reactivex.rxkotlin.Singles
//import io.reactivex.rxkotlin.addTo
//import io.reactivex.schedulers.Schedulers
//import okhttp3.ResponseBody
//import javax.inject.Inject
//
//
//@HiltViewModel
//class MatchDetailViewModel @Inject constructor(
//    private val pref: Pref,
//    private val fifaImageManager: FIFAImageManager
//) : BaseViewModel() {
//
//    var _spidDTOList = ArrayList<SpIdResult>()
//    var _sppositionDTOList = ArrayList<SpPositionResult>()
//
//    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerResult>()
//    val filteredMatchMyPlayerList = MutableLiveData<ArrayList<MatchPlayerResult>>()
//    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerResult>()
//    val filteredMatchOpponentPlayerList = MutableLiveData<ArrayList<MatchPlayerResult>>()
//
//    fun getPreData(){
//        _spidDTOList = pref.getAllSpidList() as ArrayList<SpIdResult>
//        _sppositionDTOList = pref.getAllSppositionList() as ArrayList<SpPositionResult>
//    }
//
//    fun setPlayer(matchDTO: MatchMetaDataResult) {
//        Observables.zip(
//            Observable.fromIterable(matchDTO.matchInfo[0].player),
//            Observable.fromIterable(matchDTO.matchInfo[1].player)
//        )
//            .subscribeOn(Schedulers.io())
//            .concatMapSingle { (left, right) ->
//                Log.d("hello", "concatMapSingleLeft : ${left}")
//                Log.d("hello", "concatMapSingleRight : ${right}")
//                Singles.zip( // Single to Single
//                    pickUpPlayer(left.spId, left.spPosition), // Single
//                    pickUpPlayer(right.spId, right.spPosition) // Single
//                )
//            }
////            .onErrorReturn {
////                //if (it.localizedMessage == "HTTP 403") {
////                Log.d("hello", "error test : ${it}")
////                MatchPlayerResult(
////                    "LW"
////                ) to MatchPlayerResult(
////                    "LW",
////                )
////
////                //MatchPlayerResult() to MatchPlayerResult()
////            }
//            .subscribe({ (left, right) ->
//                Log.d("hello", "left : ${left}")
//                Log.d("hello", "right : ${right}")
//                filtedMatchMyPlayerDTOList.add(left)
//                filtedMatchOpponentPlayerDTOList.add(right)
//            },{
//                Log.e("cyc", "setPlayer 실패 : ${it.localizedMessage}")
//            })
//            .addTo(disposable)
//    }
//
//    private fun pickUpPlayer(id: Int, position: Int): Single<MatchPlayerResult> {
//        return Singles.zip(
//            fifaImageManager.requestSpIdImage(id),
//            Single.just(_spidDTOList),
//            Single.just(_sppositionDTOList)
//        )
//            .subscribeOn(Schedulers.io())
////                if (it.localizedMessage == "HTTP 403") {
////                    return@onErrorReturn MatchPlayerResult("name", "desc", "imageBinaryList.toString()")
////                }
//            .map { (imageBinaryList, spIdList, spPositionList) ->
//                var name = ""
//                var desc = ""
//                spIdList?.let { spIdList ->
//                    spIdList.forEach {
//                        //Log.d("hello2", "spIdList : ${it}")
//                        if (it.id == id) {
//                            name = it.name.trim()
//                        }
//                    }
//                }
//
//                spPositionList?.let { spPositionList ->
//                    spPositionList.forEach {
//                        //Log.d("hello2", "spPositionList : ${it}")
//                        if (it.spposition == position) {
//                            desc = it.desc.trim()
//                        }
//                    }
//                }
//                val result = MatchPlayerResult(name, desc)
//                Log.d("hello", "pickUpPlayer result : ${result}")
//                result
//            }
//    }
//
//    sealed class CustomError() : Throwable() {
//        data class ImageError(
//            var messages: String = ""
//        ) : Throwable()
//    }
//
//}






// main에서 데이터 가져오기 전 코드
//@HiltViewModel
//class MatchDetailViewModel @Inject constructor(
//    private val fifaMetadataManager: FIFAMetadataManager,
//    private val pref: Pref
//
//) : ViewModel() {
////    바꾸는중
////    private val _spidDTOList = MutableLiveData<ArrayList<SpidDTO>>()
////    val spidDTOList: LiveData<ArrayList<SpidDTO>>
////        get() = _spidDTOList
////
////    private val _sppositionDTOList = MutableLiveData<ArrayList<SppositionDTO>>()
////    val sppositionDTOList: LiveData<ArrayList<SppositionDTO>>
////        get() = _sppositionDTOList
//
//
//    //과연 이게 뷰모델에서 할 필요가 있는가?
////     var _spidDTOList = MutableLiveData<ArrayList<SpidDTO>>()
////     var _sppositionDTOList = MutableLiveData<ArrayList<SppositionDTO>>()
////
//    var _spidDTOList = ArrayList<SpidDTO>()
//    var _sppositionDTOList = ArrayList<SppositionDTO>()
//
//    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerDTO>()
//    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerDTO>()
//
////    바꾸는중
////    fun requestSpid() {
////        val result = fifaMetadataManager.requestSpid()
////        result.enqueue(object : Callback<ArrayList<SpidDTO>> {
////            override fun onResponse(
////                call: Call<ArrayList<SpidDTO>>,
////                response: Response<ArrayList<SpidDTO>>
////            ) {
////                if (response.isSuccessful) {
////                    response.body()?.let {
////                        _spidDTOList.postValue(it)
////                        Log.e("cyc", "전체 선수 성공")
////                    }
////                } else {
////                    Log.e("cyc", "전체 선수 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
////                }
////            }
////            override fun onFailure(call: Call<ArrayList<SpidDTO>>, t: Throwable) {
////                Log.e("cyc", "전체 선수 통신실패 (인터넷 연결의 문제, 예외발생)")
////            }
////        })
////    }
////
////    fun requestSpposition() {
////        val result = fifaMetadataManager.requestSpposition()
////        result.enqueue(object : Callback<ArrayList<SppositionDTO>> {
////            override fun onResponse(
////                call: Call<ArrayList<SppositionDTO>>,
////                response: Response<ArrayList<SppositionDTO>>
////            ) {
////                if (response.isSuccessful) {
////                    response.body()?.let {
////                        _sppositionDTOList.postValue(it)
////                        Log.e("cyc", "전체 포지션 성공")
////                    }
////                } else {
////                    Log.e("cyc", "전체 포지션 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
////                }
////            }
////
////            override fun onFailure(call: Call<ArrayList<SppositionDTO>>, t: Throwable) {
////                Log.e("cyc", "전체 포지션 통신실패 (인터넷 연결의 문제, 예외발생)")
////
////            }
////
////        })
////    }
//
//
//
//    fun getPreData(){
//        _spidDTOList = pref.getAllSpidList() as ArrayList<SpidDTO>
//        _sppositionDTOList = pref.getAllSppositionList() as ArrayList<SppositionDTO>
//    }
//
//    fun setPlayer(matchDTO: MatchDTO) {
//        matchDTO.matchInfo[0].player.forEach {
////            Log.e("cyc", "아오아오아오아오--->${it.spId}")
//            filtedMatchMyPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
////            if(matchDTO.matchInfo[0].player.indexOf(it)==matchDTO.matchInfo[0].player.size-1){
////                _matchMyPlayerDTOList.postValue(_tempMatchMyPlayerDTOList)
////            }
//        }
//
//        matchDTO.matchInfo[1].player.forEach {
//            filtedMatchOpponentPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
////            if(matchDTO.matchInfo[1].player.indexOf(it)==matchDTO.matchInfo[1].player.size-1){
////                _matchOpponentPlayerDTOList.postValue(_tempMatchOpponentPlayerDTOList)
////            }
//        }
//    }
//
//    private fun pickUpPlayer(id: Int, position: Int): MatchPlayerDTO {
//        var name = ""
//        var desc = ""
//
//        _spidDTOList?.let { spidDTOS ->
//            spidDTOS.forEach {
//                if (it.id == id) {
//                    name = it.name
//                }
//            }
//        }
//
//        _sppositionDTOList?.let { sppositionDTOS ->
//            sppositionDTOS.forEach {
//                if (it.spposition == position) {
//                    desc = it.desc
//                }
//            }
//        }
//
//
////        _spidDTOList.value?.let { spidDTOS ->
////            spidDTOS.forEach {
////                if (it.id == id) {
////                    name = it.name
////                }
////            }
////        }
////
////        _sppositionDTOList.value?.let { sppositionDTOS ->
////            sppositionDTOS.forEach {
////                if (it.spposition == position) {
////                    desc = it.desc
////                }
////            }
////        }
//        return MatchPlayerDTO(name, desc)
//    }
//}