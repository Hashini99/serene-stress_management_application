package com.example.serene.moods
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_select_mood.*
import java.util.*
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.serene.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

//import com.example.serene.habits.AddHabits

import java.util.Objects.toString
import kotlin.Unit.toString


//
//
//        class SelectMood : AppCompatActivity() {
//            // corr   lateinit var mhappy: EditText
//            override fun onCreate(savedInstanceState: Bundle?) {
////    super.onCreate(savedInstanceState)
////    try {
////        this.supportActionBar!!.hide()
////    } catch (e: NullPointerException) {
////    }}
//                super.onCreate(savedInstanceState)
//                setContentView(R.layout.activity_select_mood)
//
//
////     val test: Any = ""
////
////     if (test == button3.text.toString()) {
////         test == "sad"
////         return
////
////     }
////
////     if (test == button2.text.toString()) {
////         test == "happy"
////         return
////     }
//
//                val sad = button3.text.toString()
//                val angry = button4.text.toString()
//                val happy = button2.text.toString()
//
////    // val test= button.text.toString()
////
////     val test=
////     if (test==button2)
////     {
////         val sad = button3.text.toString()
////     }
////      else(test==button3)
////     {
////         val sad = button3.text.toString()
////     }
//
//                val moods = hashMapOf(
//                    "user" to FirebaseAuth.getInstance().currentUser?.uid,
////            //"mood_type" to card_happy,
//                    "mood" to angry,
//                    "mood" to happy,
//                    "mood" to sad,
//
//////            "mood" to card_anger,
//////            "mood" to card_sad,
//////            "mood" to card_tired,
//                    "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
//                        Calendar.getInstance().get(Calendar.MONTH)
//                    }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
//                    "time" to "${
//                        Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//                    }:${Calendar.getInstance().get(Calendar.MINUTE)}"
//                )
//
//                Firebase.firestore.collection("mood").add(moods).addOnSuccessListener {
//
//                    startActivity(Intent(this,MoodFix::class.java))
//                    finish()
//                }
//
//
//            }
//        }



//////correctt
//class SelectMood : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_select_mood)
//
//        val sad = button3.text.toString()
//        val angry = button4.text.toString()
//        val happy = button2.text.toString()
//
//        val moods = hashMapOf(
//            "user" to FirebaseAuth.getInstance().currentUser?.uid,
//            "mood" to angry,
//            "mood" to happy,
//            "mood" to sad,
//
//
//            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
//                Calendar.getInstance().get(Calendar.MONTH)
//            }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
//            "time" to "${
//                Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//            }:${Calendar.getInstance().get(Calendar.MINUTE)}"
//        )
//
//        Firebase.firestore.collection("mood").add(moods).addOnSuccessListener {
//            startActivity(Intent(this,MoodFix::class.java))
//            finish()
//        }
//
//
//    }
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, Home::class.java))
//        finish()
//    }
//}

class SelectMood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mood)


    }

        fun Addmood(view: View) {
            when (view.id) {
                R.id.button1 -> {
                    Addmood()
                }
                R.id.button2 -> {
                    Addmood()
                }
                R.id.button3 -> {
                     Addmood()
                }
                R.id.button4 -> {
                    Addmood()
                }
                R.id.button5 -> {
                    Addmood()
                }
            }

        }

    private fun Addmood() {
//        val sad = button3.text.toString()
//        val angry = button5.text.toString()
//        val happy = button1.text.toString()
//        val neutral = button2.text.toString()
//        val contempt = button4.text.toString()


        val happy = findViewById<Button>(R.id.button1).toString()
        val contempt = findViewById<Button>(R.id.button4).text.toString()
        val sad = findViewById<Button>(R.id.button3).text.toString()
        val neutral = findViewById<Button>(R.id.button3).text.toString()
        val angry = findViewById<Button>(R.id.button3).text.toString()

//
//        val test=Button.toString()
//        if(test== happy)
//        {
//            val happy = findViewById<Button>(R.id.button1).text.toString()
//        }

        val moods = hashMapOf(
            "user" to FirebaseAuth.getInstance().currentUser?.uid,

            "mood" to happy,
            "mood" to contempt,
            "mood" to sad,
//            "mood" to sad,
           "mood" to neutral,
//            "mood" to contempt,
            "mood" to angry,


            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
                Calendar.getInstance().get(Calendar.MONTH)
            }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
            "time" to "${
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            }:${Calendar.getInstance().get(Calendar.MINUTE)}"
        )

        Firebase.firestore.collection("mood").add(moods).addOnSuccessListener {
            startActivity(Intent(this,MoodFix::class.java))
            finish()
        }


    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}
