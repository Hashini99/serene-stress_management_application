//package com.example.serene.moods
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.service.autofill.OnClickAction
//import android.view.LayoutInflater
//import android.widget.EditText
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import kotlinx.android.synthetic.main.activity_select_mood.*
//import java.util.*
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
//import androidx.fragment.app.Fragment
//import androidx.navigation.Navigation.findNavController
//import com.example.serene.*
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.firestore.FirebaseFirestore
//
////import com.example.serene.habits.AddHabits
//
//import java.util.Objects.toString
//import kotlin.Unit.toString
//
//
//
//
//class SelectMood : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_select_mood)
//
//
//    }
//
//        fun Addmood(view: View) {
//            when (view.id) {
//                R.id.button1 -> {
//                    Addmood()
//                }
//                R.id.button2 -> {
//                    Addmood()
//                }
//                R.id.button3 -> {
//                     Addmood()
//                }
//                R.id.button4 -> {
//                    Addmood()
//                }
//                R.id.button5 -> {
//                    Addmood()
//                }
//            }
//
//        }
//
////    private fun Addmood() {
////
////
////
////        val happy = findViewById<Button>(R.id.button1).toString()
////        val contempt = findViewById<Button>(R.id.button4).text.toString()
////        val sad = findViewById<Button>(R.id.button3).text.toString()
////        val neutral = findViewById<Button>(R.id.button3).text.toString()
////        val angry = findViewById<Button>(R.id.button3).text.toString()
////
////
////        val moods = hashMapOf(
////            "user" to FirebaseAuth.getInstance().currentUser?.uid,
////
////            "mood" to happy,
////            "mood" to contempt,
////            "mood" to sad,
//////            "mood" to sad,
////           "mood" to neutral,
//////            "mood" to contempt,
////            "mood" to angry,
////
////
////            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${Calendar.getInstance().get(Calendar.MONTH)+1}-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
////            "time" to "${
////                Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
////            }:${Calendar.getInstance().get(Calendar.MINUTE)}"
////        )
////
////        Firebase.firestore.collection("mood").add(moods).addOnSuccessListener {
////            startActivity(Intent(this,MoodFix::class.java))
////            finish()
////        }
////
////
////    }
//private fun Addmood() {
//
//    var selectedMood = ""
//    val happyButton = findViewById<Button>(R.id.button1)
//    val contemptButton = findViewById<Button>(R.id.button4)
//    val sadButton = findViewById<Button>(R.id.button3)
//    val neutralButton = findViewById<Button>(R.id.button2)
//    val angryButton = findViewById<Button>(R.id.button5)
//
//    // Set click listeners on all mood buttons
//    happyButton.setOnClickListener {
//        selectedMood = "happy"
//    }
//    contemptButton.setOnClickListener {
//        selectedMood = "contempt"
//    }
//    sadButton.setOnClickListener {
//        selectedMood = "sad"
//    }
//    neutralButton.setOnClickListener {
//        selectedMood = "neutral"
//    }
//    angryButton.setOnClickListener {
//        selectedMood = "angry"
//    }
//
//    // Create a hashmap with the selected mood and other data to be stored
//    val moods = hashMapOf(
//        "user" to FirebaseAuth.getInstance().currentUser?.uid,
//        "selected_mood" to selectedMood,
//        "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${Calendar.getInstance().get(Calendar.MONTH)+1}-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
//        "time" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}"
//    )
//
//    // Store the mood data in the database
//    Firebase.firestore.collection("mood").add(moods).addOnSuccessListener {
//        startActivity(Intent(this,MoodFix::class.java))
//        finish()
//    }
//}
//
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, Home::class.java))
//        finish()
//    }
//}
package com.example.serene.moods

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.Home
import com.example.serene.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class SelectMood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.s_m)
        setContentView(R.layout.activity_select_mood)

        // Find all mood buttons
        val happyButton = findViewById<Button>(R.id.button1)
        val contemptButton = findViewById<Button>(R.id.button4)
        val sadButton = findViewById<Button>(R.id.button3)
        val neutralButton = findViewById<Button>(R.id.button2)
        val angryButton = findViewById<Button>(R.id.button5)

        // Set click listeners on all mood buttons
        happyButton.setOnClickListener {
            addMood("happy")
        }
        contemptButton.setOnClickListener {
            addMood("contempt")
        }
        sadButton.setOnClickListener {
            addMood("sad")
        }
        neutralButton.setOnClickListener {
            addMood("neutral")
        }
        angryButton.setOnClickListener {
            addMood("angry")
        }
    }

    private fun addMood(selectedMood: String) {
        // Create a hashmap with the selected mood and other data to be stored
        val moods = hashMapOf(
            "user" to FirebaseAuth.getInstance().currentUser?.uid,
            "selected_mood" to selectedMood,
            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${Calendar.getInstance().get(Calendar.MONTH)+1}-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
            "time" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}"
        )

        // Store the mood data in the database
        Firebase.firestore.collection("mood").add(moods).addOnSuccessListener {
            startActivity(Intent(this, MoodFix::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}
