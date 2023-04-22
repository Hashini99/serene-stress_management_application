package com.example.serene.montly_quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.*
import com.example.serene.history.StressTrackerOverview
import com.example.serene.moods.MoodFix
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_question_one.*
import kotlinx.android.synthetic.main.quiz_result.*
import java.util.*


var tot=mark1+ mark2+ mark3+mark4+ mark5+ mark6+mark7+ mark8+ mark9+mark10
class QuizResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_result)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}


        val name : TextView = findViewById(R.id.name)
        val score : TextView = findViewById(R.id.score)
        val button : Button = findViewById(R.id.finish_btn)
        score.text = "Your Score is ${tot} out of 40"
       // val statement:TextView=findViewById(R.id.level_says)


//        if(tot<=13)
//        {
//            level_says.setText("You are facing low stress")
//        }
//        if(14<tot<=26)
//        {
//            level_says.setText("You are facing moderate stress")
//        }
//        else {
//            level_says.setText("You are facing high perceived stress")
//        }

        if (tot <= 13) {
            level_says.setText("You are facing low stress")
        } else if (tot >= 14 && tot <= 26) {
            level_says.setText("You are facing moderate stress")
        } else {
            level_says.setText("You are facing high perceived stress")
        }

        val quizresults = hashMapOf(
            "user" to FirebaseAuth.getInstance().currentUser?.uid,
            "score" to tot,
            "datetime" to Timestamp.now(),
            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${Calendar.getInstance().get(Calendar.MONTH)+1}-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
            "time" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}",
            //"statement" to statement
        )

        Firebase.firestore.collection("quiz").add(quizresults).addOnSuccessListener {
           startActivity(Intent(this,MainTasks::class.java))
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
    fun end(view: View){
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }

}