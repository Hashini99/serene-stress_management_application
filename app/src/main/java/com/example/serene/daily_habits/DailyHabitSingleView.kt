package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.serene.CreateAccount
import com.example.serene.JournalMainActivity
import com.example.serene.MainTasks
import com.example.serene.R
import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.android.synthetic.main.activity_daily_habit_single_view.*


import kotlinx.android.synthetic.main.dailyhabit_single_view.*




lateinit var documentID: String
lateinit var startTime: String
lateinit var habittitle: String

class DailyHabitSingleView : AppCompatActivity() {
    private lateinit var tvTime: TextView
    private lateinit var btnStart: Button
    private lateinit var btnReset: Button
    private var timer: CountDownTimer? = null

    private var minutesFocus: Long = 0
    private var currentTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.dailyhabit_single_view)

        documentID = intent.getStringExtra("docID").toString()

        tvTime = findViewById(R.id.tv_count_down)
        btnStart = findViewById(R.id.btn_star)
        btnReset = findViewById(R.id.btn_sto)

        btnStart.setOnClickListener {
            startTimer()
        }

        btnReset.setOnClickListener {
            resetTimer()
        }

        FirebaseFirestore.getInstance().collection("habits").document(documentID).get()
            .addOnSuccessListener {
                habittitle = it.data!!.getValue("title").toString()
                minutesFocus = it.data!!.getValue("minutesFocus").toString().toLong()

                tv_count_down.text = it.data!!.getValue("minutesFocus").toString()
                tv_count_down_title.text = it.data!!.getValue("title").toString()
            }.addOnFailureListener{
                Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
            }
    }



    private fun showSuccessDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.habit_success_dialog, null)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)

        val dialog = builder.create()

        val btnClose = dialogView.findViewById<Button>(R.id.btn_clos)
        btnClose.setOnClickListener {
            dialog.dismiss()
            // Do any other necessary actions here
        }

        dialog.show()
    }


    private fun startTimer() {
        if (timer != null) {
            timer!!.cancel()
        }

        currentTime = minutesFocus * 60 * 1000

        timer = object : CountDownTimer(currentTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime = millisUntilFinished
                updateTimer()
            }

            override fun onFinish() {
                showSuccessDialog()
                resetTimer()
                btnReset.visibility = Button.VISIBLE
            }
        }

        timer!!.start()

        btnStart.isEnabled = false
        btnReset.visibility = Button.GONE
    }

    private fun resetTimer() {
        if (timer != null) {
            timer!!.cancel()
        }

        currentTime = minutesFocus * 60 * 1000
        updateTimer()

        btnStart.isEnabled = true
        btnReset.visibility = Button.GONE
    }

    private fun updateTimer() {
        val timeString = DateUtils.formatElapsedTime(currentTime / 1000)
        tvTime.text = timeString
    }

    fun delete_habit(view: View) {
        FirebaseFirestore.getInstance().collection("habits").document(documentID).delete()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Habit Deleted Successful", Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, DailyHabitsMain::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Cannot Delete the Habit", Toast.LENGTH_LONG).show()
            }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DailyHabitsMain::class.java))
        finish()
    }
}






//package com.example.serene.daily_habits
//
//import android.content.Intent
//import android.os.Bundle
//import android.os.CountDownTimer
//import android.provider.CalendarContract
//import android.text.format.DateUtils
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Button
//import android.widget.CalendarView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModelProvider
//import androidx.work.WorkManager
//import com.example.serene.CreateAccount
//import com.example.serene.JournalMainActivity
//import com.example.serene.MainTasks
//import com.example.serene.R
//import com.google.firebase.firestore.FirebaseFirestore
////import kotlinx.android.synthetic.main.activity_daily_habit_single_view.*
//
//
//import kotlinx.android.synthetic.main.dailyhabit_single_view.*
//import java.util.*
//
//
//lateinit var documentID: String
////lateinit var startTime: String
//lateinit var habittitle: String
//
//class DailyHabitSingleView : AppCompatActivity() {
//    private lateinit var tvTime: TextView
//    private lateinit var btnStart: Button
//    private lateinit var btnReset: Button
//    private var timer: CountDownTimer? = null
//
//    private var minutesFocus: Long = 0
//    private var currentTime: Long = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//
//
//
//
//
//        super.onCreate(savedInstanceState)
//
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//        }
//
//        setContentView(R.layout.dailyhabit_single_view)
//
//        documentID = intent.getStringExtra("docID").toString()
//
//        tvTime = findViewById(R.id.tv_count_down)
//        btnStart = findViewById(R.id.btn_star)
//        btnReset = findViewById(R.id.btn_sto)
//
//        btnStart.setOnClickListener {
//            startTimer()
//        }
//
//        btnReset.setOnClickListener {
//            resetTimer()
//        }
//
//        FirebaseFirestore.getInstance().collection("habits").document(documentID).get()
//            .addOnSuccessListener {
//                habittitle = it.data!!.getValue("title").toString()
//                minutesFocus = it.data!!.getValue("minutesFocus").toString().toLong()
//
//                tv_count_down.text = it.data!!.getValue("minutesFocus").toString()
//                tv_count_down_title.text = it.data!!.getValue("title").toString()
//            }.addOnFailureListener{
//                Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
//            }
//    }
//
//
//    private fun showSuccessDialog() {
//        val dialogView = LayoutInflater.from(this).inflate(R.layout.habit_success_dialog, null)
//
//        val builder = AlertDialog.Builder(this)
//            .setView(dialogView)
//            .setCancelable(false)
//
//        val dialog = builder.create()
//
//
//
//        // Get a reference to the calendar view
//       // val calendarView = findViewById<CalendarView>(R.id.calendarView)
//        val calendarView = dialogView.findViewById<CalendarView>(R.id.calendarView)
//        val btnClose = dialogView.findViewById<Button>(R.id.btn_clos)
//        btnClose.setOnClickListener {
//            dialog.dismiss()
//
//
//
//            // Get the selected date in milliseconds
//            val selectedDate = calendarView.date
//
//            // Create an intent to add the event to the calendar
//            val intent = Intent(Intent.ACTION_INSERT)
//                .setData(CalendarContract.Events.CONTENT_URI)
//                .putExtra(CalendarContract.Events.TITLE, habittitle)
//                .putExtra(CalendarContract.Events.DESCRIPTION, "Daily Habit")
//                .putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
//                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, selectedDate)
//                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, selectedDate + minutesFocus * 60 * 1000)
//
//            // Start the calendar app with the intent
//            startActivity(intent)
//        }
//
//        dialog.show()
//    }
//
//
//    private fun startTimer() {
//        if (timer != null) {
//            timer!!.cancel()
//        }
//
//        currentTime = minutesFocus * 60 * 1000
//
//        timer = object : CountDownTimer(currentTime, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                currentTime = millisUntilFinished
//                updateTimer()
//            }
//
//            override fun onFinish() {
//                showSuccessDialog()
//                resetTimer()
//                btnReset.visibility = Button.VISIBLE
//            }
//        }
//
//        timer!!.start()
//
//        btnStart.isEnabled = false
//        btnReset.visibility = Button.GONE
//    }
//
//    private fun resetTimer() {
//        if (timer != null) {
//            timer!!.cancel()
//        }
//
//        currentTime = minutesFocus * 60 * 1000
//        updateTimer()
//
//        btnStart.isEnabled = true
//        btnReset.visibility = Button.GONE
//    }
//
//    private fun updateTimer() {
//        val timeString = DateUtils.formatElapsedTime(currentTime / 1000)
//        tvTime.text = timeString
//    }
//
//    fun delete_habit(view: View) {
//        FirebaseFirestore.getInstance().collection("habits").document(documentID).delete()
//            .addOnSuccessListener {
//                Toast.makeText(applicationContext, "Habit Deleted Successful", Toast.LENGTH_LONG)
//                    .show()
//                startActivity(Intent(this, DailyHabitsMain::class.java))
//                finish()
//            }.addOnFailureListener {
//                Toast.makeText(applicationContext, "Cannot Delete the Habit", Toast.LENGTH_LONG).show()
//            }
//    }
//
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, DailyHabitsMain::class.java))
//        finish()
//    }
//}
//
//


