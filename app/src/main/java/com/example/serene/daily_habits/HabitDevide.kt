package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.MainTasks
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_futureyou.*
import kotlinx.android.synthetic.main.activity_habit_devide.*

class HabitDevide: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_devide)
        supportActionBar?.title = getString(R.string.h_m)

        fy.setOnClickListener {
            startActivity(Intent(this, SocialHabitMain::class.java))
            finish()
        }


        ff.setOnClickListener {
            startActivity(Intent(this, DailyHabitsMain::class.java))
            finish()
        }


    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }
}