package com.example.view.fifa.ui.activity.splashactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.view.fifa.databinding.ActivitySplashBinding
import com.example.view.fifa.ui.activity.main.MainActivity
import com.example.view.fifa.ui.dialog.LoadingProgressDialog
import com.example.view.fifa.util.Pref
import com.example.view.fifa.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivitySplashBinding
//    private lateinit var loadingProgressDialog: LoadingProgressDialog

    private val viewModel: SplashViewModel by viewModels()

    private var checkSaveAllSpidList: Boolean = false
    private var checkSaveAllSppositionList: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        initData()
        initObserve()
    }

    private fun initData(){
        viewModel.requestSpid()
        viewModel.requestSpposition()
//        loadingProgressDialog = LoadingProgressDialog(this)
//        loadingProgressDialog.show()
    }

    private fun initObserve(){
        viewModel.spidDTOList.observe(this){
            pref.saveAllSpidList(it)
            checkSaveAllSpidList=true
            if(checkSaveAllSpidList && checkSaveAllSppositionList){
//                loadingProgressDialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        viewModel.sppositionDTOList.observe(this){
            pref.saveAllSppositionList(it)
            checkSaveAllSppositionList = true
            if(checkSaveAllSpidList && checkSaveAllSppositionList){
//                loadingProgressDialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}