package com.example.serene.moods

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.serene.*

//import com.example.serene.habits.AddHabits
import com.google.firebase.auth.FirebaseAuth


import kotlinx.android.synthetic.main.activity_mood_fix.*

class MoodFix : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_fix)

        //music
        music_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://soundcloud.com/soothingrelaxation/beautiful-relaxing-music-for-stress-relief-sleep-meditation-study-planet-earth?in=soothingrelaxation/sets/long-relaxing-music-extended")
            startActivity(openURL)
        }


        //nature
        nature_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://pixabay.com/images/search/nature/")
            startActivity(openURL)
        }

        //jokes
       jokes_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.rd.com/jokes/")
            startActivity(openURL)
        }

        //poems
       poem_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://allpoetry.com/poems/about/relaxing")
            startActivity(openURL)
        }
        //logout
//        logout_cardtired.setOnClickListener{
//            FirebaseAuth.getInstance().signOut()
//            startActivity(Intent(this,CreateAccount::class.java))
//            finish()
//        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}