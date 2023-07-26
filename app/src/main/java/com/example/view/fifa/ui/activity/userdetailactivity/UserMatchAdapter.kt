package com.example.view.fifa.ui.activity.userdetailactivity

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.ui.activity.searchsubactivity.RecentSearchRecyclerListener

class UserMatchAdapter(private val context: Context, private val userMatchRecyclerListener: UserMatchRecyclerListener, private val matchDTOList: ArrayList<MatchDTO>,private val nickName: String) : RecyclerView.Adapter<UserMatchViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMatchViewHolder {
        val itemBinding =
            ItemUserRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserMatchViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: UserMatchViewHolder, position: Int) {
        holder.bind(matchDTOList[position],userMatchRecyclerListener,nickName)
    }

    override fun getItemCount(): Int {
        return matchDTOList.size
    }

//    fun setSearchResult(matchDtoList: List<MatchDTO>){
//        matchDtoList?.let {
//            this.matchDtoList=it
//        }
//        notifyDataSetChanged()
//    }
}