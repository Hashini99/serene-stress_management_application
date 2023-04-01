package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.CreateAccount
import com.example.serene.Login
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_select_mood.*

//data class Quiz1(
//    val answerone:String="",
//      val mark:String=""
//        )
var mark1:Int=0
val total:Int=0

class Quiz:AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_one)

        supportActionBar?.title = getString(R.string.question_one)


        q1_option_one.setOnClickListener {
            mark1 = 0
        }
        q1_option_two.setOnClickListener {
            mark1 = 1
        }
        q1_option_three.setOnClickListener {
            mark1 = 2
        }
        q1_option_four.setOnClickListener {
            mark1 = 3
        }
        q1_option_five.setOnClickListener {
            mark1 = 4
        }


    }

private operator fun Int.plus(buttonValue: String): Int {
    return total+ mark1
}

    fun go_to_q2(view: View) {
        startActivity(Intent(this, Question2::class.java))
        finish()
    }
}

