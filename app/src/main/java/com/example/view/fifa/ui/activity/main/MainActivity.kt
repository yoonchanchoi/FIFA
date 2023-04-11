package com.example.view.fifa.ui.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.view.fifa.R
import com.example.view.fifa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun init() {
        val appbar = supportActionBar
        appbar?.let { it.title = "FIFA"}
        initData()
        initObserve()
        initListener()
    }

    private fun initData() {

    }

    private fun initListener() {
    }

    private fun initObserve() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, "FIFA 세팅 중", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }
}