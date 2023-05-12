package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.serene.JournalMainActivity
import com.example.serene.Login
import com.example.serene.R
import com.example.serene.history.HistoryMain
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_dailyhabit.*
import java.text.SimpleDateFormat
import java.util.*










class AddDailyHabits : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    // private lateinit var viewModel: AddHabitViewModel
    lateinit var saveeButton: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dailyhabit)

        supportActionBar?.title = getString(R.string.add_habit)

//        val factory = ViewModelFactory.getInstance(this)
//        viewModel = ViewModelProvider(this, factory)[AddHabitViewModel::class.java]

        saveeButton = findViewById(R.id.save_habit)

        saveeButton.setOnClickListener {


            val title = findViewById<EditText>(R.id.add_ed_title).text.toString()

            val minutesFocus =
                findViewById<EditText>(R.id.add_ed_minutes_focus).text.toString().toLong()

            val startTime = findViewById<TextView>(R.id.add_tv_start_time).text.toString()

            val priorityLevel =
                findViewById<Spinner>(R.id.sp_priority_level).selectedItem.toString()

            if (title.isNotEmpty()) {
                val habit = hashMapOf(
                    "user" to FirebaseAuth.getInstance().currentUser?.uid,
                    "title" to title,
                    //"title" to add_ed_title.text.toString(),
                    "minutesFocus" to minutesFocus,
                    //"startTime" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}",
                    "startTime" to startTime,
                    "priorityLevel" to priorityLevel
                )
                Firebase.firestore.collection("habits").add(habit).addOnSuccessListener {
                    startActivity(Intent(this, DailyHabitsMain::class.java))
                    finish()
                }


            }

        }

    }
            fun showTimePicker(view: View) {
                val dialogFragment = TimePickerFragment()
                dialogFragment.show(supportFragmentManager, "timePicker")
            }

            override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                findViewById<TextView>(R.id.add_tv_start_time).text =
                    dateFormat.format(calendar.time)
            }

            fun addCalendarEvent(view: View) {
                val calendarEvent: Calendar = Calendar.getInstance()
                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra("beginTime", calendarEvent.timeInMillis)
                intent.putExtra("allDay", true)


                intent.putExtra("allDay", true)
                intent.putExtra("rule", "FREQ=YEARLY")
                intent.putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
                intent.putExtra("title", "Calendar Event")
                startActivity(intent)
            }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DailyHabitsMain::class.java))
        finish()
    }

        }

