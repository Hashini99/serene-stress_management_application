package com.example.serene

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.academic_goal.GoalMain
import com.example.serene.daily_habits.DailyHabitsMain
import com.example.serene.daily_habits.HabitDevide
import com.example.serene.expert_support.ExpertSupportMain
import com.example.serene.history.HistoryMain
import com.example.serene.meditation.MeditationMain
import com.example.serene.moods.MoodFix
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_task.*
import kotlinx.android.synthetic.main.activity_mood_fix.*

class MainTasks: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.m_t)
        setContentView(R.layout.activity_main_task)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navmt)
        bottomNavigationView.selectedItemId = R.id.main_graph

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_graph -> {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                  //  bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
//                R.id.main_graph -> {
//                    val intent = Intent(this, MainTasks::class.java)
//                    startActivity(intent)
//                   // bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
                R.id.main_graph -> {
                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
                        // Do nothing if the selected item is already the main tasks page
                        return@setOnItemSelectedListener true
                    } else {
                        val intent = Intent(this, MainTasks::class.java)
                        startActivity(intent)
                        bottomNavigationView.selectedItemId = menuItem.itemId
                        true
                    }
                }

                R.id.charts_graph -> {
                    val intent = Intent(this, HistoryMain::class.java)
                    startActivity(intent)
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.expert_graph -> {
                    val intent = Intent(this, ExpertSupportMain::class.java)
                    startActivity(intent)
                  //  bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.settings_graph -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                  //  bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                else -> false
            }
        }
        //habit
        habit_card.setOnClickListener {
            startActivity(Intent(this,HabitDevide::class.java))
            finish()
        }

        //goal
        goal_card.setOnClickListener {
            startActivity(Intent(this,GoalMain::class.java))
            finish()
        }
        //journal
        journal_card.setOnClickListener {
            startActivity(Intent(this,JournalMainActivity::class.java))
            finish()
        }
        //med
        med_card.setOnClickListener {
            startActivity(Intent(this,MeditationMain::class.java))
            finish()
        }
    }
}