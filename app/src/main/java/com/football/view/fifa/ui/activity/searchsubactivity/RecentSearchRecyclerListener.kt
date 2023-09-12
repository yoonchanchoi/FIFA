package com.football.view.fifa.ui.activity.searchsubactivity

interface RecentSearchRecyclerListener {
    fun onItemDelete(position: Int)
    fun onItemClick(position: Int,nickname: String)
}