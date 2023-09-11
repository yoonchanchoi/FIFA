package com.example.view.fifa.viewmodels

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.managers.FIFAImageManager
import com.example.view.fifa.network.models.dto.*
import com.example.view.fifa.util.Constants
import com.example.view.fifa.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val fifaImageManager: FIFAImageManager,
    private val pref: Pref,

) : ViewModel() {

    var _spidDTOList = ArrayList<SpidDTO>()
    var _sppositionDTOList = ArrayList<SppositionDTO>()

    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerDTO>()
    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerDTO>()

//    val myPlayerBitmapList : ArrayList<Bitmap> = arrayListOf()
//    val myPlayerBitmapList = ArrayList<Bitmap>()
//    val opponentPlayerBitmapList = ArrayList<Bitmap>()


    private val _myPlayerBitmapList = MutableLiveData<ArrayList<Bitmap>>()
    val myPlayerBitmapList: LiveData<ArrayList<Bitmap>>
        get() = _myPlayerBitmapList

    private val _opponentPlayerBitmapList = MutableLiveData<ArrayList<Bitmap>>()
    val opponentPlayerBitmapList: LiveData<ArrayList<Bitmap>>
        get() = _opponentPlayerBitmapList



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
        requestSpidImage2(matchDTO.matchInfo[1].player,Constants.MY_TEAM_PLAYER_IMAGE)

        matchDTO.matchInfo[1].player.forEach {
            filtedMatchOpponentPlayerDTOList.add(pickUpPlayer(it.spId, it.spPosition))
//            if(matchDTO.matchInfo[1].player.indexOf(it)==matchDTO.matchInfo[1].player.size-1){
//                _matchOpponentPlayerDTOList.postValue(_tempMatchOpponentPlayerDTOList)
//            }
        }
        requestSpidImage2(matchDTO.matchInfo[1].player,Constants.OPPONENT_TEAM_PLAYER_IMAGE)
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


    fun requestSpidImage() {
        Log.e("cyc","비트맵 test")
        val result = fifaImageManager.requestSpidImage(280202126)
        result.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {

                    //해당 responsebody값을 여러번 호출 할 경우 (response.body().string()이렇게) 한번 밖에 호출되지 않고 다음
                    //값 부터는 똑같이 호출해도 null값이 온다. 이 이유는 해당 response값은 메모리에 저장된 상태가 아니기 때문이다.
                    // 이건 아직 확실하지 않는 부분이지만 위에 이유 때문인지 이것을 (it.byteStream()이렇게) 호출해서 바이너리 데이터를 스트림으로 받을 때도
                    // 한번만 호출되고 다음부터는 null 값이 나온다..이것 때문에 여러 테스트할 때 계속 null이 나오는데 원인을 몰라서  아에 잘못한 줄 하고 3이나 샵질했다...아...ㅇㅅㅇ;;
                        Log.e("cyc", "선수 액션 이미지 성공")
                        val input = it.byteStream()
                        val bmp = BitmapFactory.decodeStream(input)
//                        testBitmapList.add(bmp)

//                        Log.e("cyc","디테일 뷰몯델 testBitmapList[0]-->${testBitmapList[0]}")
                    }
                } else {
                    Log.e("cyc", "선수 액션 이미지 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("cyc", "선수 액션 이미지 통신실패 (인터넷 연결의 문제, 예외발생)")

            }
        })
    }


    fun requestSpidImage2(playerList : ArrayList<PlayerDTO>, whoseTeam: String) {
        val bitmapList = ArrayList<Bitmap>()
        playerList.forEach { plyaerDTO ->
            val result = fifaImageManager.requestSpidImage(plyaerDTO.spId)
            result.enqueue(object : Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        response.body()?.let {

                            val input = it.byteStream()
                            val bmp = BitmapFactory.decodeStream(input)
                            bitmapList.add(bmp)
                            if(playerList.indexOf(plyaerDTO) == playerList.size-1){
                                if(whoseTeam.equals(Constants.MY_TEAM_PLAYER_IMAGE)){
                                    _myPlayerBitmapList.postValue(bitmapList)
                                }else{
                                    _opponentPlayerBitmapList.postValue(bitmapList)
                                }
                            }
                        }
                        Log.e("cyc", "유저 경기 정보---성공")

                    }else{
                        Log.e("cyc", "유저 경기 정보---통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("cyc", "유저 경기 정보---통신실패 (인터넷 연결의 문제, 예외발생)")
                }
            })
            // 밖에서 받는 것을 한다.
            // synchronized로 처리할 될 내부의 repsonse를 뺄수가 없다..일단 다시 해본다 오늘 시도했지만 터졋다..
        }
    }
}




















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