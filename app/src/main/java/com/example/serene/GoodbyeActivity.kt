package com.example.serene

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GoodbyeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goodbye)
        navigateToRegister()
    }

    private fun navigateToRegister() {
        val intent = Intent(this, CreateAccount::class.java)
        startActivity(intent)
        finish()
    }
}