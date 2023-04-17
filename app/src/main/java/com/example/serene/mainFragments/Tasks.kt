package com.example.serene.mainFragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.CreateAccount
import com.example.serene.JournalAddEditNoteActivity
import com.example.serene.R
import com.example.serene.meditation.MeditationMain
import kotlinx.android.synthetic.main.main_tasks.*

class Tasks : AppCompatActivity() {

    companion object {
        const val NAME = "NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_tasks)
        journal_card.setOnClickListener { startActivity(Intent(this, JournalAddEditNoteActivity::class.java)) }
        meditation_card.setOnClickListener { startActivity(Intent(this, MeditationMain::class.java)) }
//        card_tired.setOnClickListener { startActivity(Intent(this, ::class.java)) }
//        card_anger.setOnClickListener { startActivity(Intent(this, CardAnger::class.java))
        imageButton3.setOnClickListener { startActivity(Intent(this, CreateAccount::class.java)) }
        }
//        val intent = getIntent()
//        val name = intent.getStringExtra(NAME)
//        name_text.text = "HEY!!  " + name

//        FirebaseAuth.getInstance()
//        checkUser()

//        logout_choosemood.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
//        }


    }

