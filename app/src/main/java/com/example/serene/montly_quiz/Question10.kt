package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_question_ten.*


var mark10:Int =0
class Question10: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_ten)

        supportActionBar?.title = getString(R.string.question_ten)


        q10_option_one.setOnClickListener {
            mark10 = 0
        }
        q10_option_two.setOnClickListener {
            mark10 = 1
        }
        q10_option_three.setOnClickListener {
            mark10 = 2
        }
        q10_option_four.setOnClickListener {
            mark10 = 3
        }
        q10_option_five.setOnClickListener {
            mark10 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4+mark5+mark6+mark7+mark8+mark9+mark10
    }
    fun go_to_re(view: View) {
        startActivity(Intent(this,  QuizResult::class.java))
        finish()
    }
}

