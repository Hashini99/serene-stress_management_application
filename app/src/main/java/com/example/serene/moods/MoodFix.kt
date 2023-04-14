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
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0fjFCCGiRYPcDLHrfqthcT")
            startActivity(openURL)
        }
//        movie_card.setOnClickListener { startActivity(Intent(this, movietired::class.java)) }
//        jokes.setOnClickListener {
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse("http://www.laughfactory.com/jokes/latest-jokes")
//            startActivity(openURL)
//        }

        //nature
        nature_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0fjFCCGiRYPcDLHrfqthcT")
            startActivity(openURL)
        }

        //jokes
       jokes_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0fjFCCGiRYPcDLHrfqthcT")
            startActivity(openURL)
        }

        //poems
       poem_card.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://open.spotify.com/playlist/0fjFCCGiRYPcDLHrfqthcT")
            startActivity(openURL)
        }
        //logout
        logout_cardtired.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,CreateAccount::class.java))
            finish()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}