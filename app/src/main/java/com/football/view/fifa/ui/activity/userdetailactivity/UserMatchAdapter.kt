package com.football.view.fifa.ui.activity.userdetailactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.football.view.fifa.databinding.ItemUserRecordBinding
import com.football.view.fifa.network.models.dto.MatchDTO
import com.football.view.fifa.network.models.dto.UserDTO

class UserMatchAdapter(private val context: Context, private val userMatchRecyclerListener: UserMatchRecyclerListener, private val matchDTOList: ArrayList<MatchDTO>,private val userDTO: UserDTO) : RecyclerView.Adapter<UserMatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMatchViewHolder {
        val itemBinding =
            ItemUserRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserMatchViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: UserMatchViewHolder, position: Int) {
        holder.bind(matchDTOList[position],userMatchRecyclerListener,userDTO)
    }

    override fun getItemCount(): Int {
        return matchDTOList.size
    }
}