package com.example.serene.montly_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import kotlinx.android.synthetic.main.activity_question_nine.*
import kotlinx.android.synthetic.main.activity_question_one.*


var mark9:Int =0
class Qusetion9 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_nine)

     //   supportActionBar?.title = getString(R.string.question_nine)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}


        q9_option_one.setOnClickListener {
            mark9 = 0
        }
        q9_option_two.setOnClickListener {
            mark9 = 1
        }
        q9_option_three.setOnClickListener {
            mark9 = 2
        }
        q9_option_four.setOnClickListener {
            mark9 = 3
        }
        q9_option_five.setOnClickListener {
            mark9 = 4
        }


    }

    private operator fun Int.plus(buttonValue: String): Int {
        return  mark1+mark2+mark3+mark4+mark5+mark6+mark7+mark8+mark9
    }
    fun go_to_q10(view: View) {
        startActivity(Intent(this, Question10::class.java))
        finish()
    }
    fun previous(view: View){
        startActivity(Intent(this, Question8::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Question8::class.java))
        finish()
    }
}

