package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.CreateAccount
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.activity_question_two.*

var mark2:Int =0
//val total2:Int=0

class Question2: AppCompatActivity() {

    val answertwo:String=""

    //var mark2:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_two)

        supportActionBar?.title = getString(R.string.question_two)



        q2_option_one.setOnClickListener {
            mark2 = 0
        }
        q2_option_two.setOnClickListener {
            mark2 = 1
        }
        q2_option_three.setOnClickListener {
            mark2 = 2
        }
        q2_option_four.setOnClickListener {
            mark2 = 3
        }
//        q2_option_five.setOnClickListener {
//            mark1 = 4
//        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return mark1+ mark2
    }
    fun go_to_q3(view: View){
        startActivity(Intent(this, Question3::class.java))
        finish()
    }

}