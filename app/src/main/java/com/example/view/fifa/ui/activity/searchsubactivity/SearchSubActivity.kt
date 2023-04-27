package com.example.view.fifa.ui.activity.searchsubactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.view.fifa.databinding.ActivityMainBinding
import com.example.view.fifa.databinding.ActivitySearchSubBinding
import com.example.view.fifa.network.models.dto.MatchDTO
import com.example.view.fifa.viewmodels.SearchSubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSubBinding

    private lateinit var searchResultAdapter: SearchResultAdapter

    private val viewModel: SearchSubViewModel by viewModels()

    private lateinit var matchDtoList: ArrayList<MatchDTO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchSubBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_search_sub)
        init()
    }

    private fun init() {
        setSearchResultAdapter()
        initData()
        initLisnear()
        initObserve()
    }

    private fun initData() {
        binding.etSearch.requestFocus()
    }

    private fun initLisnear() {
        binding.etSearch.apply {
            this.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (binding.etSearch.text.toString().isNotEmpty()) {
                        binding.btnTextClear.visibility = View.VISIBLE
                    } else {
                        binding.btnTextClear.visibility = View.INVISIBLE

                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })

            this.setOnEditorActionListener { textView, actionId, keyEvent ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        Log.e("cyc","검색 클릭시")
                        viewModel.requestUserInfo(textView.text.toString())
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
        //텍스트 전체 삭제 리스너
        binding.btnTextClear.setOnClickListener {
            binding.etSearch.text.clear()
        }
    }

    private fun initObserve() {
        viewModel.matchDTOList.observe(this){
            Log.e("cyc","SearchSubActivity--->observe")
            Log.e("cyc","SearchSubActivity--size-->${it.size}")
            searchResultAdapter.setSearchResult(it)
        }
    }

    private fun setSearchResultAdapter() {
        searchResultAdapter = SearchResultAdapter()
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        binding.rv.apply {
            layoutManager = searchLinearLayoutManager
            adapter = searchResultAdapter
            //            this.scrollToPosition(searchAdapter.itemCount - 1) // 해당 포지션으로 스크롤 이동
        }
    }
}