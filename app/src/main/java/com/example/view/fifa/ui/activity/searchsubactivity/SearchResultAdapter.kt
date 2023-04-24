package com.example.view.fifa.ui.activity.searchsubactivity

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.network.models.dto.MatchInfoDTO

class SearchResultAdapter : RecyclerView.Adapter<SearchResultViewHolder>(){
    //    private lateinit var arrayImage : ArrayList<Drawable>

    private lateinit var matchDtoList : ArrayList<MatchDTO>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val itemBinding =
            ItemUserRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(matchDtoList[position])
    }

    override fun getItemCount(): Int {
        return matchDtoList.size
    }

    fun setSearchResult(matchDtoList: ArrayList<MatchDTO>){
        this.matchDtoList = matchDtoList
    }


}