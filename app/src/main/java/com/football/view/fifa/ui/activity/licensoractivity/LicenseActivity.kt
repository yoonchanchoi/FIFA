package com.football.view.fifa.ui.activity.licensoractivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.football.view.fifa.R
import com.football.view.fifa.databinding.ActivityLicenseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LicenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLicenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLicenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_licensor_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        val appbar = supportActionBar
        appbar?.let {
            it.title = getString(R.string.app_name)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

}