package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_question_three.*
import kotlinx.android.synthetic.main.activity_question_two.*

var mark3:Int =0


class Question3 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_three)

        supportActionBar?.title = getString(R.string.question_three)

        q3_option_one.setOnClickListener {
            mark3 = 0
        }
        q3_option_two.setOnClickListener {
            mark3 = 1
        }
        q3_option_three.setOnClickListener {
            mark3 = 2
        }
        q3_option_four.setOnClickListener {
            mark3 = 3
        }
        q3_option_five.setOnClickListener {
            mark3 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+ mark3
    }

    fun go_to_q4(view: View) {
        startActivity(Intent(this, Question4::class.java))
        finish()
    }
}