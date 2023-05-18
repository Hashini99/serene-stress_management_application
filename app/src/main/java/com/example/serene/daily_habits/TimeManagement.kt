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

class TimeManagement : AppCompatActivity() {
    private lateinit var habitsList: ListView
    private lateinit var habitTitle: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_habits)
        supportActionBar?.title = getString(R.string.t_m)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        habitsList = findViewById(R.id.habits_list)
        habitTitle = findViewById(R.id.habit_title)

        val Financialhabit = listOf(
            Habit(
                "Use a planner or scheduling app to prioritize tasks and deadlines",
                5
            ),
            Habit("Set aside designated study time each day or week", 3),
            Habit("Use the Pomodoro technique (25-minute focused work intervals followed by 5-minute breaks) to increase productivity", 7),
            Habit("Avoid procrastination and work on tasks as they arise", 4),
            Habit("Evaluate and adjust your time management strategies as needed.", 8),
            Habit("Avoid multitasking and focus on one task at a time", 6),
            Habit("Break up larger tasks into smaller, manageable steps", 1),
            Habit(
                "Take breaks and give yourself time to rest and recharge",
                2
            ),

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
                            startActivity(Intent(this, SocialHabitMain::class.java))
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
}