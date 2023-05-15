package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.JournalMainActivity
import com.example.serene.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Habit(val title: String, val difficulty: Int)

class SocialHabit : AppCompatActivity() {
    private lateinit var habitsList: ListView
    private lateinit var habitTitle: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_habits)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        habitsList = findViewById(R.id.habits_list)
        habitTitle = findViewById(R.id.habit_title)

        val socialhabits = listOf(
            Habit("Attend campus events or join clubs and organizations to meet new people", 5),
            Habit("Connect with classmates and form study groups", 3),
            Habit("Join a mentorship or leadership program", 7),
            Habit("Practice active listening and engage in meaningful conversations with others", 4),
            Habit("Volunteer with a community organization or non-profit", 8),
            Habit("Attend social events, such as parties or game nights", 6),
            Habit("Participate in team-building exercises or retreats", 9),
            Habit("Attend cultural events or festivals to learn about different perspectives and traditions", 2),
            Habit("Build and maintain positive relationships with family and friends", 1),
            Habit("Seek help or support from a trusted friend or mentor if needed", 10)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, socialhabits.map { it.title })
        habitsList.adapter = adapter

//        habitsList.setOnItemClickListener { _, _, position, _ ->
//            val selectedHabit =socialhabits[position]
//            habitTitle.text = selectedHabit.title
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("How hard is this habit for you?")
//            val ratingBar = RatingBar(this)
//            ratingBar.numStars = 10
//            ratingBar.rating = selectedHabit.difficulty.toFloat()
//            builder.setView(ratingBar)
//            builder.setPositiveButton("OK") { _, _ ->
//                val userId = auth.currentUser?.uid
//                val rating = ratingBar.rating.toInt()
        habitsList.setOnItemClickListener { _, _, position, _ ->
            val selectedHabit = socialhabits[position]
            habitTitle.text = selectedHabit.title
            val builder = AlertDialog.Builder(this)
            builder.setTitle("How hard is this habit for you?")
            val seekBar = SeekBar(this)
            seekBar.max = 100
            seekBar.progress = selectedHabit.difficulty * 10
            builder.setView(seekBar)
            builder.setPositiveButton("OK") { _, _ ->
                val userId = auth.currentUser?.uid
                val rating = seekBar.progress / 10

                if (title.isNotEmpty()) {
                    val socialhabits= hashMapOf(
                        "user" to FirebaseAuth.getInstance().currentUser?.uid,
                        "title" to selectedHabit.title,
                        "difficulty" to rating
                    )


                        Firebase.firestore.collection("futurehabits").add(socialhabits).addOnSuccessListener {
                            startActivity(Intent(this, DailyHabitsMain::class.java))
                            finish()
                        }


                    }
                 else {
                    // User is not authenticated, show a sign-in dialog
                    val signInDialog = AlertDialog.Builder(this)
                        .setTitle("Sign in required")
                        .setMessage("You need to sign in to save your habit rating.")
                        .setPositiveButton("Sign in") { _, _ ->
                            // TODO: Show a sign-in screen
                        }
                        .setNegativeButton("Cancel", null)
                        .create()
                    signInDialog.show()
                }
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SocialHabitMain::class.java))
        finish()
    }
}