package com.example.view.fifa.ui.activity.userdetailactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.searchstudy.util.Pref
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.databinding.ActivityUserDetailBinding
import com.example.view.fifa.network.models.dto.UserDTO
import com.example.view.fifa.ui.activity.searchsubactivity.RecentSearchAdapter
import com.example.view.fifa.ui.activity.searchsubactivity.RecentSearchRecyclerListener
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

//    private lateinit var searchDataList: ArrayList<UserDTO>
    private lateinit var binding: ActivityUserDetailBinding
//    private lateinit var searchRecentAdapter: RecentSearchAdapter
//    private val viewModel: SearchSubViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init() {
        initData()
        initObserve()
        initLisnear()
    }


    private fun initData(){

    }

    private fun initObserve(){

    }

    private fun initLisnear(){

    }

}


