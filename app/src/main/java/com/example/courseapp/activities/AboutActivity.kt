package com.example.courseapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.courseapp.R
import com.example.courseapp.databinding.ActivityAboutBinding
import com.google.android.gms.ads.AdRequest

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}