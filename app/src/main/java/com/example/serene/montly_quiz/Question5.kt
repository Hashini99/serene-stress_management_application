package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_five.*
import kotlinx.android.synthetic.main.activity_question_one.*

var mark5:Int =0
class Question5 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_five)

        supportActionBar?.title = getString(R.string.question_five)


        q5_option_one.setOnClickListener {
            mark5 = 0
        }
        q5_option_two.setOnClickListener {
            mark5 = 1
        }
        q5_option_three.setOnClickListener {
            mark5 = 2
        }
        q5_option_four.setOnClickListener {
            mark5 = 3
        }
        q5_option_five.setOnClickListener {
            mark5 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4+mark5
    }
    fun go_to_q6(view: View) {
        startActivity(Intent(this, Question6::class.java))
        finish()
    }
}

