package com.example.view.fifa.ui.activity.searchsubactivity

import android.provider.ContactsContract.CommonDataKinds.Nickname

interface RecentSearchRecyclerListener {
    fun onItemDelete(position: Int)
    fun onItemClick(position: Int,nickname: String)
}