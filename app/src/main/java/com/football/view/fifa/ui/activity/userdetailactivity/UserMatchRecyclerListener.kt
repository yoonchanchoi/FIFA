package com.football.view.fifa.ui.activity.userdetailactivity

import com.football.view.fifa.network.models.dto.MatchDTO

interface UserMatchRecyclerListener {
    fun onItemClick(matchDTO: MatchDTO, nickName: String)
    fun onErrorItemClick()
}