package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_question_six.*

var mark6:Int =0
class Question6 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_six)

       // supportActionBar?.title = getString(R.string.question_six)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}


        q6_option_one.setOnClickListener {
            mark6 = 0
        }
        q6_option_two.setOnClickListener {
            mark6 = 1
        }
        q6_option_three.setOnClickListener {
            mark6 = 2
        }
        q6_option_four.setOnClickListener {
            mark6 = 3
        }
        q6_option_five.setOnClickListener {
            mark6 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4+mark5+ mark6
    }
    fun go_to_q7(view: View) {
        startActivity(Intent(this, Question7::class.java))
        finish()
    }
    fun previous(view: View){
        startActivity(Intent(this, Question5::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Question5::class.java))
        finish()
    }
}

