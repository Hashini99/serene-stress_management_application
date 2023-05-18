package com.example.serene.expert_support

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.*
import com.example.serene.daily_habits.DailyHabitsMain
import com.example.serene.history.HistoryMain
import com.example.serene.meditation.MeditationMain
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_task.*
import kotlinx.android.synthetic.main.activity_main_task.med_card
import kotlinx.android.synthetic.main.expert_support_main.*

class ExpertSupportMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.e_m)
        setContentView(R.layout.expert_support_main)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_naves)

        bottomNavigationView.selectedItemId = R.id.expert_graph

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
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.charts_graph -> {
                    val intent = Intent(this, HistoryMain::class.java)
                    startActivity(intent)
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
//                R.id.expert_graph -> {
//                    val intent = Intent(this, ExpertSupportMain::class.java)
//                    startActivity(intent)
//                    true
//                }
                R.id.expert_graph -> {
                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
                        // Do nothing if the selected item is already the expert support page
                        return@setOnItemSelectedListener true
                    } else {
                        val intent = Intent(this, ExpertSupportMain::class.java)
                        startActivity(intent)
                        bottomNavigationView.selectedItemId = menuItem.itemId
                        true
                    }
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
        //meet expert
        meetexpert_card.setOnClickListener {
            startActivity(Intent(this, ExpertList::class.java))
            finish()
        }

        //booking
        booking_card.setOnClickListener {
            startActivity(Intent(this, ChannelList::class.java))
            finish()
        }
        //jorney
        journey_card.setOnClickListener {
            startActivity(Intent(this, JourneyMain::class.java))
            finish()
        }
//        //med
//        med_card.setOnClickListener {
//            startActivity(Intent(this, MeditationMain::class.java))
//            finish()
//        }
    }
}