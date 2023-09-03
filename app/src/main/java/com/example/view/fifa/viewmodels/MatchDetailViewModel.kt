package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.managers.FIFAMetadataManager
import com.example.view.fifa.network.models.dto.*
import com.example.view.fifa.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val fifaMetadataManager: FIFAMetadataManager,
//    private val pref: Pref

) : ViewModel() {
//    바꾸는중
    private val _spidDTOList = MutableLiveData<ArrayList<SpidDTO>>()
    val spidDTOList: LiveData<ArrayList<SpidDTO>>
        get() = _spidDTOList

    private val _sppositionDTOList = MutableLiveData<ArrayList<SppositionDTO>>()
    val sppositionDTOList: LiveData<ArrayList<SppositionDTO>>
        get() = _sppositionDTOList


    //과연 이게 뷰모델에서 할 필요가 있는가?
//     var _spidDTOList = MutableLiveData<ArrayList<SpidDTO>>()
//     var _sppositionDTOList = MutableLiveData<ArrayList<SppositionDTO>>()
//
//    var _spidDTOList = ArrayList<SpidDTO>()
//    var _sppositionDTOList = ArrayList<SppositionDTO>()

    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerDTO>()
    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerDTO>()

//    바꾸는중
    fun requestSpid() {
        val result = fifaMetadataManager.requestSpid()
        result.enqueue(object : Callback<ArrayList<SpidDTO>> {
            override fun onResponse(
                call: Call<ArrayList<SpidDTO>>,
                response: Response<ArrayList<SpidDTO>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _spidDTOList.postValue(it)
                        Log.e("cyc", "전체 선수 성공")
                    }
                } else {
                    Log.e("cyc", "전체 선수 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }
            override fun onFailure(call: Call<ArrayList<SpidDTO>>, t: Throwable) {
                Log.e("cyc", "전체 선수 통신실패 (인터넷 연결의 문제, 예외발생)")
            }
        })
    }

    fun requestSpposition() {
        val result = fifaMetadataManager.requestSpposition()
        result.enqueue(object : Callback<ArrayList<SppositionDTO>> {
            override fun onResponse(
                call: Call<ArrayList<SppositionDTO>>,
                response: Response<ArrayList<SppositionDTO>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _sppositionDTOList.postValue(it)
                        Log.e("cyc", "전체 포지션 성공")
                    }
                } else {
                    Log.e("cyc", "전체 포지션 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ArrayList<SppositionDTO>>, t: Throwable) {
                Log.e("cyc", "전체 포지션 통신실패 (인터넷 연결의 문제, 예외발생)")

            }

        })
    }



//    fun getPreData(){
//        _spidDTOList = pref.getAllSpidList() as ArrayList<SpidDTO>
//        _sppositionDTOList = pref.getAllSppositionList() as ArrayList<SppositionDTO>
//    }

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


        _spidDTOList.value?.let { spidDTOS ->
            spidDTOS.forEach {
                if (it.id == id) {
                    name = it.name
                }
            }
        }

        _sppositionDTOList.value?.let { sppositionDTOS ->
            sppositionDTOS.forEach {
                if (it.spposition == position) {
                    desc = it.desc
                }
            }
        }
        return MatchPlayerDTO(name, desc)
    }
}