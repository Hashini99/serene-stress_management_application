package com.example.serene.meditation
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.Home
import com.example.serene.MainTasks
import com.example.serene.R


class MeditationMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation_main)
        supportActionBar?.title = getString(R.string.me)
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val firstTime = sharedPreferences.getString("FirstTimeInstall", "")

        if(firstTime.equals("Yes")) {
            val intent = Intent(this, MeditationSecond::class.java)
            startActivity(intent)
            finish()

        } else {

            val editor = sharedPreferences.edit()
            editor.apply {
                putString("FirstTimeInstall", "Yes")
            }.apply()

        }

    }

    fun getstart(view: View) {

        val intent = Intent(this, MeditationSecond::class.java)
        startActivity(intent)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }
}