package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import com.example.serene.history.MoodHistory
import com.example.serene.history.StressTrackerOverview
import kotlinx.android.synthetic.main.activity_futureyou.*
import kotlinx.android.synthetic.main.activity_history_main.*

class FutureYou : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futureyou)
        supportActionBar?.title = getString(R.string.h_m)

        sh.setOnClickListener {
            startActivity(Intent(this, SocialHabit::class.java))
            finish()
        }


        fh.setOnClickListener {
            startActivity(Intent(this, FinancialHabit::class.java))
            finish()
        }

        cd.setOnClickListener {
            startActivity(Intent(this, CareerDevelopment::class.java))
            finish()
        }

        tm.setOnClickListener {
            startActivity(Intent(this, TimeManagement::class.java))
            finish()
        }
    }
}