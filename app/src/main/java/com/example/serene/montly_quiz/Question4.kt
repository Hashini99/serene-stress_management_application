package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_four.*
import kotlinx.android.synthetic.main.activity_question_one.*


var mark4:Int =0
class Question4 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_four)

       // supportActionBar?.title = getString(R.string.question_four)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}


        q4_option_one.setOnClickListener {
            mark4 = 0
        }
        q4_option_two.setOnClickListener {
            mark4 = 1
        }
        q4_option_three.setOnClickListener {
            mark4 = 2
        }
        q4_option_four.setOnClickListener {
            mark4 = 3
        }
        q4_option_five.setOnClickListener {
            mark4 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4
    }
    fun go_to_q5(view: View) {
        startActivity(Intent(this, Question5::class.java))
        finish()
    }
    fun previous(view: View){
        startActivity(Intent(this, Question3::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Question3::class.java))
        finish()
    }
}

