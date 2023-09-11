package com.example.view.fifa.ui.activity.matchdetailactivity

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.view.fifa.databinding.ItemMatchPlayerBinding
import com.example.view.fifa.network.models.dto.MatchPlayerDTO


////이미지 test
//class MatchPlayerAdapter(private val context: Context ,private val matchPlayerDTOList: ArrayList<MatchPlayerDTO>) : RecyclerView.Adapter<MatchPlayerViewHolder>(){
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerViewHolder {
//        val itemBinding =
//            ItemMatchPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MatchPlayerViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: MatchPlayerViewHolder, position: Int) {
//        holder.bind(context, matchPlayerDTOList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return matchPlayerDTOList.size
//    }
//}
////이미지 test


//이미지 test 바꾸기 전
class MatchPlayerAdapter(private val context: Context ,private val matchPlayerDTOList: ArrayList<MatchPlayerDTO>,private val bitmapList: ArrayList<Bitmap>) : RecyclerView.Adapter<MatchPlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerViewHolder {
        val itemBinding =
            ItemMatchPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchPlayerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MatchPlayerViewHolder, position: Int) {
        holder.bind(context, matchPlayerDTOList[position], bitmapList[position])
    }

    override fun getItemCount(): Int {
        return matchPlayerDTOList.size
    }
}
//이미지 test 바꾸기 전
