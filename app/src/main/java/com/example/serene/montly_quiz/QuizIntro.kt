package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.serene.CreateAccount
import com.example.serene.R
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
}