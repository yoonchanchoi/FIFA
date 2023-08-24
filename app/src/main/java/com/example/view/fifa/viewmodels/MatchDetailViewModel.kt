package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    //Test
    private val _spidDTOList2 = MutableLiveData<ArrayList<SpidDTO>>()
    val spidDTOList2: LiveData<ArrayList<SpidDTO>>
        get() = _spidDTOList2

    //Test2
    private val _sppositionDTOList2 = MutableLiveData<ArrayList<SppositionDTO>>()
    val sppositionDTOList2: LiveData<ArrayList<SppositionDTO>>
        get() = _sppositionDTOList2

//    private val _checkSpidDTOList = MutableLiveData<Boolean>()
//    val checkSpidDTOList: LiveData<Boolean>
//        get() = _checkSpidDTOList
//
//    private val _checkSppositionDTOList = MutableLiveData<Boolean>()
//    val checkSppositionDTOList: LiveData<Boolean>
//        get() = _checkSppositionDTOList

//    var checkSpidDTOList = false
//    var checkSppositionDTOList = false

//    private val _matchMyPlayerDTOList = MutableLiveData<ArrayList<MatchPlayerDTO>>()
//    val matchMyPlayerDTOList: LiveData<ArrayList<MatchPlayerDTO>>
//        get() = _matchMyPlayerDTOList
//
//    private val _matchOpponentPlayerDTOList = MutableLiveData<ArrayList<MatchPlayerDTO>>()
//    val matchOpponentPlayerDTOList: LiveData<ArrayList<MatchPlayerDTO>>
//        get() = _matchOpponentPlayerDTOList
//
//    private val _tempMatchMyPlayerDTOList = ArrayList<MatchPlayerDTO>()
//
//    private val _tempMatchOpponentPlayerDTOList = ArrayList<MatchPlayerDTO>()


    val tempMatchMyPlayerDTOList = ArrayList<MatchPlayerDTO>()

    val tempMatchOpponentPlayerDTOList = ArrayList<MatchPlayerDTO>()


//    private var _spidDTOList2 = ArrayList<SpidDTO>()
//
//    private var _sppositionDTOList2 = ArrayList<SppositionDTO>()



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
                        _spidDTOList2.postValue(it)
//                        _checkSpidDTOList.postValue(true)
//                        _spidDTOList2 = it
                        Log.e("cyc","디테일 뷰모델 요청받은 바로 후---_spidDTOList2-->${_spidDTOList2}")

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
                        _sppositionDTOList2.postValue(it)
//                        _checkSppositionDTOList.postValue(true)

//                        _sppositionDTOList2 = it
                        Log.e(
                            "cyc",
                            "디테일 뷰모델 요청받은 바로 후---_sppositionDTOList2-->${_sppositionDTOList2}"
                        )


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


    //아...어케해야되냐......
    fun setPlayer(matchDTO: MatchDTO) {
        matchDTO.matchInfo[0].player.forEach {
            Log.e("cyc", "아오아오아오아오--->${it.spId}")
            tempMatchMyPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
//            _tempMatchMyPlayerDTOList.add(pickUpPlayer(it.spId,it.spPosition))
//
//            if(matchDTO.matchInfo[0].player.indexOf(it)==matchDTO.matchInfo[0].player.size-1){
//                _matchMyPlayerDTOList.postValue(_tempMatchMyPlayerDTOList)
//            }
        }

        matchDTO.matchInfo[1].player.forEach {
            tempMatchOpponentPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
//            _tempMatchOpponentPlayerDTOList.add(pickUpPlayer(it.spId,it.spPosition))
//
//            if(matchDTO.matchInfo[1].player.indexOf(it)==matchDTO.matchInfo[1].player.size-1){
//                _matchOpponentPlayerDTOList.postValue(_tempMatchOpponentPlayerDTOList)
//            }
        }
        Log.e("cyc", "디테일 뷰모델---tempMatchMyPlayerDTOList-->${tempMatchMyPlayerDTOList}")

        Log.e("cyc", "디테일 뷰모델---tempMatchOpponentPlayerDTOList-->${tempMatchOpponentPlayerDTOList}")


    }


    //아...어케해야되냐......
    private fun pickUpPlayer(id: Int, position: Int): MatchPlayerDTO {
        var name = ""
        var desc = ""

//        Log.e("cyc","디테일 뷰모델---_spidDTOList2-->${_spidDTOList2.value}")
//        Log.e("cyc","디테일 뷰모델---_sppositionDTOList2-->${_sppositionDTOList2.value}")


//        _spidDTOList2.forEach {
//            Log.e("cyc","디테일 뷰모델---id-->${id} <======> it.id--->${it.id}")
//            if (it.id == id) {
//                Log.e("cyc","선수 이름 찾는 뷰모델 로직 도는가?")
//                name = it.name
//            }
//        }
//
//        _sppositionDTOList2.forEach {
//            Log.e("cyc","디테일 뷰모델---position-->${position} <======> it.spposition--->${it.spposition}")
//
//            if (it.spposition == position) {
//                Log.e("cyc","선수 포지션 찾는 뷰모델 로직 도는가?")
//
//                desc = it.desc
//            }
//        }
        _spidDTOList2.value?.let { spidDTOS ->
            spidDTOS.forEach {
//                Log.e("cyc","디테일 뷰모델---id-->${id} <======> it.id--->${it.id}")
                if (it.id == id) {
                    Log.e("cyc", "")
                    Log.e("cyc", "디테일 뷰모델---id-->${id} <======> it.id--->${it.id}")
                    Log.e("cyc", "선수 이름 찾는 뷰모델 로직 도는가?")
                    name = it.name
                    Log.e("cyc", "디테일 뷰모델---찾는 선수의 id -->${id}")
                    Log.e("cyc", "디테일 뷰모델---찾는 선수의 이름 name-->${name}")
                    Log.e("cyc", "")

                }
            }

        }

        _sppositionDTOList2.value?.let { sppositionDTOS ->
            sppositionDTOS.forEach {
//                Log.e("cyc","디테일 뷰모델---position-->${position} <======> it.spposition--->${it.spposition}")

                if (it.spposition == position) {
                    Log.e("cyc", "")
                    Log.e(
                        "cyc",
                        "디테일 뷰모델---position-->${position} <======> it.spposition--->${it.spposition}"
                    )
                    Log.e("cyc", "선수 포지션 찾는 뷰모델 로직 도는가?")
                    desc = it.desc
                    Log.e("cyc", "디테일 뷰모델---찾는 선수의 포지션 position-->${position}")
                    Log.e("cyc", "디테일 뷰모델---찾는 선수의 포지션명칭 desc-->${desc}")
                    Log.e("cyc", "")

                }
            }

        }
        return MatchPlayerDTO(name, desc)
    }


//    //test 개별데이터로 선수 이름값이 불러지는지
//    fun requestSpid() {
//        val testSpid= 254000027
//        val result = fifaMetadataManager.testRequestSpid(testSpid)
//        result.enqueue(object : Callback<SpidDTO> {
//            override fun onResponse(
//                call: Call<SpidDTO>,
//                response: Response<SpidDTO>
//            ) {
//                Log.e("cyc", "뷰모텔 테스트 매타데이터----->>${response.body()}")
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        _spidDTOList.postValue(it)
////                        _checkSpidDTOList.postValue(true)
////                        _spidDTOList2 = it
//                        Log.e("cyc", "지호스 디테일 뷰모델 요청받은 바로 후---_spidDTOList-->${_spidDTOList}")
//
//                        Log.e("cyc", "성공")
//                    }
//                } else {
//                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
//                }
//            }
//
//            override fun onFailure(call: Call<SpidDTO>, t: Throwable) {
//                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
//
//            }
//
//        })
//    }
}