package com.football.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.football.view.fifa.network.managers.FIFAMetadataManager
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fifaMetadataManager: FIFAMetadataManager,

    ) : ViewModel() {

    private val _spidDTOList = MutableLiveData<ArrayList<SpIdResult>>()
    val spidDTOList: LiveData<ArrayList<SpIdResult>>
        get() = _spidDTOList

    private val _sppositionDTOList = MutableLiveData<ArrayList<SpPositionResult>>()
    val sppositionDTOList: LiveData<ArrayList<SpPositionResult>>
        get() = _sppositionDTOList

    fun requestSpid() {
        val result = fifaMetadataManager.requestSpid()
        result.enqueue(object : Callback<ArrayList<SpIdResult>> {
            override fun onResponse(
                call: Call<ArrayList<SpIdResult>>,
                response: Response<ArrayList<SpIdResult>>
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

            override fun onFailure(call: Call<ArrayList<SpIdResult>>, t: Throwable) {
                Log.e("cyc", "전체 선수 통신실패 (인터넷 연결의 문제, 예외발생)")
            }
        })
    }

    fun requestSpposition() {
        val result = fifaMetadataManager.requestSpposition()
        result.enqueue(object : Callback<ArrayList<SpPositionResult>> {
            override fun onResponse(
                call: Call<ArrayList<SpPositionResult>>,
                response: Response<ArrayList<SpPositionResult>>
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

            override fun onFailure(call: Call<ArrayList<SpPositionResult>>, t: Throwable) {
                Log.e("cyc", "전체 포지션 통신실패 (인터넷 연결의 문제, 예외발생)")

            }

        })
    }


}