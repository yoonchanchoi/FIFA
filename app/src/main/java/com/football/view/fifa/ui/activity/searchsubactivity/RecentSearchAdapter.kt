package com.football.view.fifa.ui.activity.searchsubactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.football.view.fifa.databinding.ItemRecentSearchBinding
import com.football.view.fifa.network.models.dto.UserDTO

class RecentSearchAdapter(private val recentSearchRecyclerListener:RecentSearchRecyclerListener,private val searchDataList: ArrayList<UserDTO>) : RecyclerView.Adapter<RecentSearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val itemBinding =
            ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentSearchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(searchDataList[position],recentSearchRecyclerListener)
    }

    override fun getItemCount(): Int {
        return searchDataList.size
    }
}