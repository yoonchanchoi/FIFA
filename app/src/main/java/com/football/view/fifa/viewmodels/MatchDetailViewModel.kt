package com.football.view.fifa.viewmodels

import androidx.lifecycle.ViewModel
import com.football.view.fifa.base.BaseViewModel
import com.football.view.fifa.network.models.dto.MatchMetaDataResult
import com.football.view.fifa.network.models.dto.MatchPlayerResult
import com.football.view.fifa.network.models.dto.SpIdResult
import com.football.view.fifa.network.models.dto.SpPositionResult
import com.football.view.fifa.util.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MatchDetailViewModel @Inject constructor(
    private val pref: Pref,
) : BaseViewModel() {

    var _spidDTOList = ArrayList<SpIdResult>()
    var _sppositionDTOList = ArrayList<SpPositionResult>()

    val filtedMatchMyPlayerDTOList = ArrayList<MatchPlayerResult>()
    val filtedMatchOpponentPlayerDTOList = ArrayList<MatchPlayerResult>()

    fun getPreData(){
        _spidDTOList = pref.getAllSpidList() as ArrayList<SpIdResult>
        _sppositionDTOList = pref.getAllSppositionList() as ArrayList<SpPositionResult>
    }

    fun setPlayer(matchDTO: MatchMetaDataResult) {
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

    private fun pickUpPlayer(id: Int, position: Int): MatchPlayerResult {
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
        return MatchPlayerResult(name, desc)
    }
}