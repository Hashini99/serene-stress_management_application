package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CareerDevelopment : AppCompatActivity() {
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

        val Financialhabit = listOf(
            Habit(
                "Connect with alumni or professionals in your field of interest",
                5
            ),
            Habit("Create a professional online presence, such as a LinkedIn profile or personal website", 3),
            Habit("Stay up-to-date on industry trends and news", 7),
            Habit(
                "Set and work towards long-term career goals.",
                4
            ),
            Habit("Build a personal brand and establish yourself as an expert in your field", 8),
            Habit("Set and work towards long-term career goals", 6),
            Habit("Participate in team-building exercises or retreats", 9),
            Habit(
                "Develop your soft skills, such as communication and problem-solving abilities",
                2
            ),
            Habit("Attend career fairs and networking events to explore job opportunities", 1),
            Habit("Seek help or support from a trusted friend or mentor if needed", 10)
        )

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, Financialhabit.map { it.title })
        habitsList.adapter = adapter

//
        habitsList.setOnItemClickListener { _, _, position, _ ->
            val selectedHabit = Financialhabit[position]
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
                    val socialhabits = hashMapOf(
                        "user" to FirebaseAuth.getInstance().currentUser?.uid,
                        "title" to selectedHabit.title,
                        "difficulty" to rating
                    )


                    Firebase.firestore.collection("futurehabits").add(socialhabits)
                        .addOnSuccessListener {
                            startActivity(Intent(this, DailyHabitsMain::class.java))
                            finish()
                        }


                } else {
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