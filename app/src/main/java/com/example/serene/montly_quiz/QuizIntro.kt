package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.serene.CreateAccount
import com.example.serene.Home
import com.example.serene.R
import com.example.serene.history.HistoryMain
import com.google.firebase.auth.FirebaseAuth

class QuizIntro: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_intro)


        supportActionBar?.title = getString(R.string.qi)
    }

    fun start(view: View) {
        startActivity(Intent(this, Quiz::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}