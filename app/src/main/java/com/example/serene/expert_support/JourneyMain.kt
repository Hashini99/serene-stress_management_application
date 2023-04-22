package com.example.serene.expert_support

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.serene.JournalMainActivity
import com.example.serene.R
import com.example.serene.databinding.ActivityJourneyMainBinding
//import com.example.serene.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class JourneyMain : AppCompatActivity() {
    private lateinit var binding: ActivityJourneyMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_journey_main)
        supportActionBar?.title = getString(R.string.m_mj)
        binding.pager.adapter = object: FragmentStateAdapter(this) {
            override fun getItemCount() = 3
            override fun createFragment(position: Int) = TimelineDayFragment.instantiate(position)
        }

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = "Session ${(position + 1)}"
        }.attach()

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ExpertSupportMain::class.java))
        finish()
    }
}