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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.serene.*
//import com.example.serene.habits.AddHabits

import kotlinx.android.synthetic.main.activity_login.*





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




class SelectMood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_mood)

        val sad = button3.text.toString()
        val angry = button4.text.toString()
        val happy = button2.text.toString()

        val moods = hashMapOf(
            "user" to FirebaseAuth.getInstance().currentUser?.uid,
            "mood" to angry,
            "mood" to happy,
            "mood" to sad,


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
}
