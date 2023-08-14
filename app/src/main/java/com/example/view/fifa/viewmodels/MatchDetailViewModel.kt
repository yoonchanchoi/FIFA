
package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.managers.FIFAManager
import com.example.view.fifa.network.managers.FIFAMetadataManager
import com.example.view.fifa.network.models.dto.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MatchDetailViewModel @Inject constructor(
//    private val fifaManager: FIFAManager,
    private val fifaMetadataManager: FIFAMetadataManager
) : ViewModel() {
//    //Test
//    private val _spidDTOList = MutableLiveData<ArrayList<SpidDTO>>()
//    val spidDTOList: LiveData<ArrayList<SpidDTO>>
//        get() = _spidDTOList
//
//    //Test2
//    private val _sppositionDTOList = MutableLiveData<ArrayList<SppositionDTO>>()
//    val sppositionDTOList: LiveData<ArrayList<SppositionDTO>>
//        get() = _sppositionDTOList
//

    private val _matchMyPlayerDTOList = MutableLiveData<ArrayList<MatchPlayerDTO>>()
    val matchMyPlayerDTOList: LiveData<ArrayList<MatchPlayerDTO>>
        get() = _matchMyPlayerDTOList

    private val _matchOpponentPlayerDTOList = MutableLiveData<ArrayList<MatchPlayerDTO>>()
    val matchOpponentPlayerDTOList: LiveData<ArrayList<MatchPlayerDTO>>
        get() = _matchOpponentPlayerDTOList

    private var _spidDTOList2 = ArrayList<SpidDTO>()

    private var _sppositionDTOList2 = ArrayList<SppositionDTO>()

    fun requestSpid() {
        val result = fifaMetadataManager.requestSpid()
        result.enqueue(object : Callback<ArrayList<SpidDTO>> {
            override fun onResponse(
                call: Call<ArrayList<SpidDTO>>,
                response: Response<ArrayList<SpidDTO>>
            ) {
                Log.e("cyc", "뷰모텔 테스트 매타데이터----->>${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
//                        _spidDTOList.postValue(it)
                        _spidDTOList2 = it
                        Log.e("cyc", "성공")
                    }
                } else {
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ArrayList<SpidDTO>>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")

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
                Log.e("cyc", "뷰모텔 테스트 매타데이터----->>${response.body()}")
                if (response.isSuccessful) {
                    response.body()?.let {
//                        _sppositionDTOList.postValue(it)
                        _sppositionDTOList2 = it
                        Log.e("cyc", "성공")
                    }
                } else {
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ArrayList<SppositionDTO>>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")

            }

        })
    }

    fun setPlayer(matchDTO: MatchDTO){
        matchDTO.matchInfo[0].player.forEach { playDto ->
            playDto.spId
            _spidDTOList2.forEach { spidDto ->
                spidDto.id
            }
        }
        matchDTO.matchInfo[1].player.forEach {

        }
    }


//    private fun pickUpPlayer(id: Int, position: Int): ArrayList<MatchPlayerDTO>{
//        val tempMatchPlayer = ArrayList<MatchPlayerDTO>()
//        val name =""
//        _spidDTOList2.forEach {
//            if(it.id==id){
//
//            }
//        }
//        tempMatchPlayer.add(MatchPlayerDTO(,))
//    }

}