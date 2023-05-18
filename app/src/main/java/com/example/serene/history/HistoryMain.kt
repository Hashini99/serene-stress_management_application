package com.example.serene.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.Home
import com.example.serene.MainTasks
import com.example.serene.Profile

import com.example.serene.R
import com.example.serene.academic_goal.GoalMain
import com.example.serene.daily_habits.DailyHabitsMain
import com.example.serene.expert_support.ExpertSupportMain
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_history_main.*


class HistoryMain  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_main)
        supportActionBar?.title = getString(R.string.h_m)

        mj_card.setOnClickListener {
            startActivity(Intent(this, MoodHistory::class.java))
            finish()
        }

        //goal
        qu_card.setOnClickListener {
            startActivity(Intent(this, StressTrackerOverview::class.java))
            finish()
        }


        //nav bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navhi)
        bottomNavigationView.selectedItemId = R.id.charts_graph

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_graph -> {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.main_graph -> {
                    val intent = Intent(this, MainTasks::class.java)
                    startActivity(intent)
                    //bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
//                R.id.charts_graph -> {
//                    val intent = Intent(this, HistoryMain::class.java)
//                    startActivity(intent)
//                  //  bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
                R.id.charts_graph -> {
                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
                        // Do nothing if the selected item is already the history page
                        return@setOnItemSelectedListener true
                    } else {
                        val intent = Intent(this, HistoryMain::class.java)
                        startActivity(intent)
                        bottomNavigationView.selectedItemId = menuItem.itemId
                        true
                    }
                }
                R.id.expert_graph -> {
                    val intent = Intent(this, ExpertSupportMain::class.java)
                    startActivity(intent)
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.settings_graph -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    //bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                else -> false
            }

        }

    }
}