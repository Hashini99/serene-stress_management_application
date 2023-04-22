package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_eight.*
import kotlinx.android.synthetic.main.activity_question_one.*

var mark8:Int =0

class Question8 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_eight)

        //supportActionBar?.title = getString(R.string.question_eight)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}



        q8_option_one.setOnClickListener {
            mark8 = 0
        }
        q8_option_two.setOnClickListener {
            mark8 = 1
        }
        q8_option_three.setOnClickListener {
            mark8 = 2
        }
        q8_option_four.setOnClickListener {
            mark8 = 3
        }
        q8_option_five.setOnClickListener {
            mark8 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4+mark5+mark6+mark7+mark8
    }
    fun go_to_q9(view: View) {
        startActivity(Intent(this, Qusetion9::class.java))
        finish()
    }
    fun previous(view: View){
        startActivity(Intent(this, Question7::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Question7::class.java))
        finish()
    }
}

