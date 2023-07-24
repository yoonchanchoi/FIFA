package com.example.view.fifa.ui.activity.userdetailactivity

import android.provider.ContactsContract.CommonDataKinds.Nickname

interface UserMatchRecyclerListener {
    fun onItemDelete(position: Int)
    fun onItemClick(position: Int)
}