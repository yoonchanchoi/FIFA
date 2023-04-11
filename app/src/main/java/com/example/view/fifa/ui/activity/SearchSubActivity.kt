package com.example.view.fifa.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.view.fifa.databinding.ActivityMainBinding
import com.example.view.fifa.databinding.ActivitySearchSubBinding

class SearchSubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSubBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchSubBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_search_sub)
    }
}