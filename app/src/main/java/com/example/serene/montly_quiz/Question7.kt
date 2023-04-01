package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_question_seven.*

var mark7:Int =0
class Question7 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_seven)

        supportActionBar?.title = getString(R.string.question_seven)


        q7_option_one.setOnClickListener {
            mark7 = 0
        }
        q7_option_two.setOnClickListener {
            mark7 = 1
        }
        q7_option_three.setOnClickListener {
            mark7 = 2
        }
        q7_option_four.setOnClickListener {
            mark7 = 3
        }
        q7_option_five.setOnClickListener {
            mark7 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4+mark5+mark6+mark7
    }
    fun go_to_q8(view: View) {
        startActivity(Intent(this, Question8::class.java))
        finish()
    }
}

