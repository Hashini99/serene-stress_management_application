package com.example.serene


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.serene.academic_goal.AddGoal
import com.example.serene.chatbot.BotActivity
import com.example.serene.expert_support.ExpertSupportMain
import com.example.serene.history.HistoryMain
import com.example.serene.home_status.CalmStatus
import com.example.serene.home_status.Motivational_Status
import com.example.serene.meditation.MeditationMain
import com.example.serene.montly_quiz.Quiz
import com.example.serene.montly_quiz.QuizIntro
import com.example.serene.moods.SelectMood
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main_task.*
import kotlinx.android.synthetic.main.activity_profile.*

class Home: AppCompatActivity() {
    val db = Firebase.firestore

    private lateinit var calendarView: CalendarView
    lateinit var imageView: ImageView
    //val images = listOf(R.drawable.na6, R.drawable.na8, R.drawable.na9,R.drawable.na10)
    val images = listOf(R.drawable.bbg, R.drawable.pbg, R.drawable.gbg,R.drawable.ybg)
    var currentIndex = 0
    val handler = Handler()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_home)

//calender view
        // initialize the CalendarView
        calendarView = findViewById(R.id.calendarView)


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val note = retrieveNoteForDate(year, month, dayOfMonth)

            if (note.isNotEmpty()) {
                // display the existing note to the user using an AlertDialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("DateMinder for $year/$month/$dayOfMonth")
                builder.setMessage(note)

                builder.setPositiveButton("Update") { dialog, _ ->
                    val updateBuilder = AlertDialog.Builder(this)
                    updateBuilder.setTitle("Update Note")

                    val input = EditText(this)
                    input.setText(note) // Set the existing note as the initial text in the EditText
                    updateBuilder.setView(input)

                    updateBuilder.setPositiveButton("Save") { _, _ ->
                        val updatedNote = input.text.toString()
                        // Update the note in your data storage
                        updateNoteForDate(year, month, dayOfMonth, updatedNote)
                    }

                    updateBuilder.setNegativeButton("Cancel") { _, _ ->
                        // Do nothing
                    }

                    updateBuilder.show()
                }

                builder.setNegativeButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }

                builder.show()
            } else {
                // create an AlertDialog to prompt the user for a note
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Add DateMinder")

                val input = EditText(this)
                builder.setView(input)

                builder.setPositiveButton("Save") { dialog, _ ->
                    val note = input.text.toString()
                    // store the note in your data storage
                    saveNoteForDate(year, month, dayOfMonth, note)
                }

                builder.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }

                builder.show()
            }
        }




       // bg image loop
        imageView = findViewById(R.id.imageView_h)

        handler.postDelayed(object : Runnable {
            override fun run() {
                currentIndex++
                if (currentIndex >= images.size) {
                    currentIndex = 0
                }
                imageView.setImageResource(images[currentIndex])
                handler.postDelayed(this, 40000) // 40 seconds delay
            }
        }, 40000) // 40 seconds delay for first image




    FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {

            display_name.text = it.data!!.getValue("name").toString()


        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Cannot Get Data from Server", Toast.LENGTH_LONG)
                .show()
        }
//nav bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navho)

//        // Create a ColorStateList object from the selector XML file
//        val colorStateList = resources.getColorStateList(R.color.bottom_nav_colors, null)
//// Set the color of the selected item to the ColorStateList
//        bottomNavigationView.itemTextColor = colorStateList
//        bottomNavigationView.itemIconTintList = colorStateList
//


        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
//                R.id.home_graph -> {
//                    val intent = Intent(this, Home::class.java)
//                    startActivity(intent)
//                    //bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
                R.id.home_graph -> {
                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
                        // Do nothing if the selected item is already the home page
                        return@setOnItemSelectedListener true
                    } else {
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        bottomNavigationView.selectedItemId = menuItem.itemId
                        true
                    }
                }
                R.id.main_graph -> {
                    val intent = Intent(this, MainTasks::class.java)
                    startActivity(intent)
                  //  bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.charts_graph -> {
                    val intent = Intent(this, HistoryMain::class.java)
                    startActivity(intent)
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.expert_graph -> {
                    val intent = Intent(this, ExpertSupportMain::class.java)
                    startActivity(intent)
                   // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.settings_graph -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    //bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                else -> false
            }

        }

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navho)
//
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.home_graph -> {
//                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
//                        // Do nothing if the selected item is already the home page
//                        return@setOnItemSelectedListener true
//                    } else {
//                        val intent = Intent(this, Home::class.java)
//                        startActivity(intent)
//                        bottomNavigationView.selectedItemId = menuItem.itemId
//                        true
//                    }
//                }
//                R.id.main_graph -> {
//                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
//                        // Do nothing if the selected item is already the main tasks page
//                        return@setOnItemSelectedListener true
//                    } else {
//                        val intent = Intent(this, MainTasks::class.java)
//                        startActivity(intent)
//                        bottomNavigationView.selectedItemId = menuItem.itemId
//                        true
//                    }
//                }
//                R.id.charts_graph -> {
//                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
//                        // Do nothing if the selected item is already the history page
//                        return@setOnItemSelectedListener true
//                    } else {
//                        val intent = Intent(this, HistoryMain::class.java)
//                        startActivity(intent)
//                        bottomNavigationView.selectedItemId = menuItem.itemId
//                        true
//                    }
//                }
//                R.id.expert_graph -> {
//                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
//                        // Do nothing if the selected item is already the expert support page
//                        return@setOnItemSelectedListener true
//                    } else {
//                        val intent = Intent(this, ExpertSupportMain::class.java)
//                        startActivity(intent)
//                        bottomNavigationView.selectedItemId = menuItem.itemId
//                        true
//                    }
//                }
//                R.id.settings_graph -> {
//                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
//                        // Do nothing if the selected item is already the profile page
//                        return@setOnItemSelectedListener true
//                    } else {
//                        val intent = Intent(this, Profile::class.java)
//                        startActivity(intent)
//                        bottomNavigationView.selectedItemId = menuItem.itemId
//                        true
//                    }
//                }
//                else -> false
//            }
//        }

        mood_card.setOnClickListener {
            startActivity(Intent(this,SelectMood::class.java))
            finish()
        }
        //que
        mq_card.setOnClickListener {
            startActivity(Intent(this, QuizIntro::class.java))
            finish()
        }
        /////status
        status.setOnClickListener {
            startActivity(Intent(this,StatusActivity::class.java))
            finish()
        }
        statusC.setOnClickListener {
            startActivity(Intent(this,CalmStatus::class.java))
            finish()
        }
        statusM.setOnClickListener {
            startActivity(Intent(this,Motivational_Status::class.java))
            finish()
        }

    }

    private fun retrieveNoteForDate(year: Int, month: Int, dayOfMonth: Int): String {
        val sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)
        return sharedPreferences.getString("$year-$month-$dayOfMonth", "") ?: ""
    }
    private fun saveNoteForDate(year: Int, month: Int, dayOfMonth: Int, note: String) {
        val sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("$year-$month-$dayOfMonth", note)
        editor.apply()
    }
    private fun updateNoteForDate(year: Int, month: Int, dayOfMonth: Int, updatedNote: String) {
        val sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("$year-$month-$dayOfMonth", updatedNote)
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
    fun chat(view: View){
        startActivity(Intent(this,BotActivity ::class.java))
        finish()
    }
}


