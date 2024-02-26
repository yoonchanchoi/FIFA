package com.football.view.fifa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.football.view.fifa.base.BaseViewModel
import com.football.view.fifa.network.managers.FIFAManager
import com.football.view.fifa.network.models.dto.SeasonIdResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fifaManager: FIFAManager,
) : BaseViewModel() {

    private val _spIdDTOList = MutableLiveData<ArrayList<SpIdResult>>()
    val spIdDTOList: LiveData<ArrayList<SpIdResult>>
        get() = _spIdDTOList

    private val _spPositionDTOList = MutableLiveData<ArrayList<SpPositionResult>>()
    val spPositionResultList: LiveData<ArrayList<SpPositionResult>>
        get() = _spPositionDTOList

    private val _seasonIdResult = MutableLiveData<ArrayList<SeasonIdResult>>()
    val seasonIdResult: LiveData<ArrayList<SeasonIdResult>>
        get() = _seasonIdResult


    fun requestSpId() {
        fifaManager.requestSpId()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.e("cyc", "전체 선수 통신 성공")
                _spIdDTOList.postValue(it)
            }, {
                Log.e("cyc", "전체 선수 통신 실패 : ${it}")
            })
            .addTo(disposable)
    }

    fun requestSpPosition() {
        fifaManager.requestSpPosition()
            .subscribeOn(Schedulers.io())
            .subscribe({ positionList ->
                Log.e("cyc", "전체 포지션 성공")
                positionList?.let {
                    _spPositionDTOList.postValue(it)
                } ?: run {
                    Log.e("cyc", "전체 포지션 값이 null")
                }
            }, {
                Log.e("cyc", "전체 포지션 통신실패 : ${it}")
            })
            .addTo(disposable)
    }

    fun requestSeasonIdResult(){
        fifaManager.requestSeasonId()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.e("cyc", "전체 seasonId 데이터 통신 성공")
                _seasonIdResult.postValue(it)
            }, {
                Log.e("cyc", "전체 seasonId 데이터 통신 실패 : ${it}")
            })
            .addTo(disposable)
    }
}