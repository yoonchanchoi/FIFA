package com.example.view.fifa.ui.activity.userdetailactivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemUserRecordBinding
import com.example.view.fifa.network.models.dto.MatchDTO

class UserMatchAdapter : RecyclerView.Adapter<UserMatchViewHolder>(){
    //    private lateinit var arrayImage : ArrayList<Drawable>

    private var matchDtoList : List<MatchDTO> = listOf()
//    private var matchDtoList = mutableListOf<MatchDTO>()
//    private var matchDtoList = mutableListOf<MatchDTO>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMatchViewHolder {
        val itemBinding =
            ItemUserRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserMatchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserMatchViewHolder, position: Int) {
        holder.bind(matchDtoList[position])
    }

    override fun getItemCount(): Int {
        return matchDtoList.size
    }

    fun setSearchResult(matchDtoList: List<MatchDTO>){
//        this.matchDtoList = matchDtoList
//        Log.e("cyc","SearchResultAdapter--->setData")
        matchDtoList?.let {
            this.matchDtoList=it
        }
        notifyDataSetChanged()
    }
}