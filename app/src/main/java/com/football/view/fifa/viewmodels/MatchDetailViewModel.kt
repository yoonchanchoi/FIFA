package com.football.view.fifa.viewmodels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.football.view.fifa.base.BaseViewModel
import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MatchPlayerResult
import com.football.view.fifa.network.models.dto.PlayerResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject


@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val pref: Pref,
) : BaseViewModel() {

    var _spidDTOList = ArrayList<SpIdResult>()
    var _sppositionDTOList = ArrayList<SpPositionResult>()

    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerResult>()
    val filteredMatchMyPlayerList = MutableLiveData<ArrayList<MatchPlayerResult>>()
    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerResult>()
    val filteredMatchOpponentPlayerList = MutableLiveData<ArrayList<MatchPlayerResult>>()

    fun getPreData(){
        _spidDTOList = pref.getAllSpidList() as ArrayList<SpIdResult>
        _sppositionDTOList = pref.getAllSppositionList() as ArrayList<SpPositionResult>
    }

    fun setPlayer(matchDTO: MatchMetaDataResult) {
        Singles.zip(
            Single.just(matchDTO.matchInfo[0].player),
            Single.just(matchDTO.matchInfo[1].player)
        )
            .subscribeOn(Schedulers.io())
            .subscribe({ (left, right) ->

                left.forEach {
                    filtedMatchMyPlayerDTOList.add(pickUpPlayer(it))
                }
                right.forEach {
                    filtedMatchOpponentPlayerDTOList.add(pickUpPlayer(it))
                }
            },{

            })
            .addTo(disposable)
    }

    private fun pickUpPlayer(playerResult: PlayerResult): MatchPlayerResult {
        var name = ""
        var desc = ""

        _spidDTOList?.let { spidDTOS ->
            spidDTOS.forEach {
                if (it.id == playerResult.spId) {
                    name = it.name

                }
            }
        }

        _sppositionDTOList?.let { sppositionDTOS ->
            sppositionDTOS.forEach {
                if (it.spposition == playerResult.spPosition) {
                    desc = it.desc
                }
            }
        }
        return MatchPlayerResult(name, desc, playerResult.spId, playerResult.spPosition, playerResult.spGrade, playerResult.status)
    }
}






