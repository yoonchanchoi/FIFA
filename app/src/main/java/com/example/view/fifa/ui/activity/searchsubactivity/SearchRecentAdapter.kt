package com.example.view.fifa.ui.activity.searchsubactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemRecentSearchBinding
import com.example.view.fifa.network.models.dto.MatchDTO

class SearchRecentAdapter : RecyclerView.Adapter<SearchRecentViewHolder>(){

    // 최근기록이름 데이터



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecentViewHolder {
        val itemBinding =
            ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRecentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchRecentViewHolder, position: Int) {
        holder.bind(matchDtoList[position])
    }

    override fun getItemCount(): Int {
        return matchDtoList.size
    }

    fun setSearchRecent(matchDtoList: List<MatchDTO>){
        matchDtoList?.let {
            this.matchDtoList=it
        }
        notifyDataSetChanged()
    }
}