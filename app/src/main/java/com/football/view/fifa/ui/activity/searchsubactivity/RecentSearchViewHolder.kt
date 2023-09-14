package com.football.view.fifa.ui.activity.searchsubactivity

import androidx.recyclerview.widget.RecyclerView
import com.football.view.fifa.databinding.ItemRecentSearchBinding
import com.football.view.fifa.network.models.dto.UserInfoResult

class RecentSearchViewHolder(private val binding: ItemRecentSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(userDTO: UserInfoResult, recentSearchRecyclerListener: RecentSearchRecyclerListener) {
        binding.tvName.text = userDTO.nickname
        binding.tvLevel.text = userDTO.level.toString()

        //삭제 버튼 생성
        binding.btnDeleteRecent.setOnClickListener {
            recentSearchRecyclerListener.onItemDelete(
                bindingAdapterPosition
//                        adapterPosition
            )
        }
        //최근 기록 아이템 클릭 이벤트
        binding.clRecentSearchItem.setOnClickListener {
            recentSearchRecyclerListener.onItemClick(
                bindingAdapterPosition,userDTO.nickname
            )
        }
    }
}