package com.football.view.fifa.ui.activity.userdetailactivity

import com.football.view.fifa.network.models.dto.MatchMetaDataResult

interface UserMatchRecyclerListener {
    fun onItemClick(matchDTO: MatchMetaDataResult, nickName: String)
    fun onErrorItemClick()
}