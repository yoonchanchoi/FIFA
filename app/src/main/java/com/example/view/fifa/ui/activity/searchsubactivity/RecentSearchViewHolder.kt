package com.example.view.fifa.ui.activity.searchsubactivity

import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemRecentSearchBinding
import com.example.view.fifa.network.models.dto.UserDTO

class RecentSearchViewHolder(private val binding: ItemRecentSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(userDTO: UserDTO, recentSearchRecyclerListener: RecentSearchRecyclerListener) {
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