package com.example.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.view.fifa.network.managers.FIFAImageManager
import com.example.view.fifa.network.managers.FIFAMetadataManager
import com.example.view.fifa.network.models.dto.SpidDTO
import com.example.view.fifa.network.models.dto.SppositionDTO
import com.example.view.fifa.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fifaMetadataManager: FIFAMetadataManager,

    ) : ViewModel() {

    private val _spidDTOList = MutableLiveData<ArrayList<SpidDTO>>()
    val spidDTOList: LiveData<ArrayList<SpidDTO>>
        get() = _spidDTOList

    private val _sppositionDTOList = MutableLiveData<ArrayList<SppositionDTO>>()
    val sppositionDTOList: LiveData<ArrayList<SppositionDTO>>
        get() = _sppositionDTOList

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
//                        pref.saveAllSppositionList(it)
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


}