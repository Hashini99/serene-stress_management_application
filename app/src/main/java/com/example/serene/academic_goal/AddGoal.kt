package com.example.serene.academic_goal

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.CreateAccount
import com.example.serene.Login
import com.example.serene.R
import com.example.serene.daily_habits.TimePickerFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_acdemic_goal_adding.*
import kotlinx.android.synthetic.main.activity_update_profile.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AddGoal: AppCompatActivity(), View.OnClickListener {

    lateinit var myCalendar: Calendar

    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var finalDate = 0L
    var finalTime = 0L




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acdemic_goal_adding)

        dateEdt.setOnClickListener(this)
        timeEdt.setOnClickListener(this)
        saveBtn.setOnClickListener(this)


        //setUpSpinner()
    }

//    private fun setUpSpinner() {
//        val adapter =
//            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, labels)
//
//        labels.sort()
//
//        spinnerCategory.adapter = adapter
//    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dateEdt -> {
                setListener()
            }
            R.id.timeEdt -> {
                setTimeListener()
            }
            R.id.saveBtn -> {
                saveTodo()
            }
        }

    }

    private fun saveTodo() {



        val goalname = titleInpLay.editText?.text.toString()
        val steps = taskInpLay.editText?.text.toString()
        //val duedate = findViewById<TextView>(R.id.dateEdt).toString().toLong()
//         val goalname = titleInpLay.text.toString()
//
//         val steps = taskInpLay.text.toString()
//
        //val duedate = dateEdt.toString().toLong()

        if (title.isNotEmpty()) {
            val goal = hashMapOf(
                "user" to FirebaseAuth.getInstance().currentUser?.uid,
                "title" to goalname,
                "tasks" to steps,
                //"title" to add_ed_title.text.toString(),
                "startTime" to "${
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                }:${Calendar.getInstance().get(Calendar.MINUTE)}",
                "datetime" to Timestamp.now(),
                "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
                    Calendar.getInstance().get(Calendar.MONTH)
                }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
             //   "duedate" to duedate,


            )
//                    viewModel.saveHabit(habit)
//                    finish()
            Firebase.firestore.collection("goals").add(goal).addOnSuccessListener {
                startActivity(Intent(this, CreateAccount::class.java))
                finish()
            }
        }else {
            Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT)
                .show()
        }
    }








//        fun save_goal(view: View) {
////
////        val goalname = findViewById<EditText>(R.id.titleInpLay).text.toString()
////
////        val steps = findViewById<EditText>(R.id.taskInpLay).text.toString()
////
////        val duedate = findViewById<TextView>(R.id.dateEdt).toString().toLong()
//
//            val goalname = titleInpLay.editText?.text.toString()
//            val steps = taskInpLay.editText?.text.toString()
//           // val duedate = dateEdt.editText?.text.toString().toLong()
////         val goalname = titleInpLay.text.toString()
////
////         val steps = taskInpLay.text.toString()
////
////        val duedate = dateEdt.toString().toLong()
//
//        if (title.isNotEmpty()) {
//            val goal = hashMapOf(
//                "user" to FirebaseAuth.getInstance().currentUser?.uid,
//                "title" to goalname,
//                "tasks" to steps,
//                //"title" to add_ed_title.text.toString(),
//                "startTime" to "${
//                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//                }:${Calendar.getInstance().get(Calendar.MINUTE)}",
//                "datetime" to Timestamp.now(),
//                "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
//                    Calendar.getInstance().get(Calendar.MONTH)
//                }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
//                //"duedate" to duedate,
//
//
//                )
////                    viewModel.saveHabit(habit)
////                    finish()
//            Firebase.firestore.collection("goals").add(goal).addOnSuccessListener {
//                startActivity(Intent(this, CreateAccount::class.java))
//                finish()
//            }
//        }else {
//        Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT)
//            .show()
//    }
//    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_add, menu)
//        return true
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        //return when (item.itemId) {
//        //R.id.action_save -> {
//        val goalname = titleInpLay.text.toString()
//
//        val steps = taskInpLay.text.toString()
//
//        val duedate = dateEdt.toString().toLong()
//
//        if (title.isNotEmpty()) {
//            val goal = hashMapOf(
//                "user" to FirebaseAuth.getInstance().currentUser?.uid,
//                "title" to goalname,
//                "tasks" to steps,
//                //"title" to add_ed_title.text.toString(),
//                "startTime" to "${
//                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
//                }:${Calendar.getInstance().get(Calendar.MINUTE)}",
//                "datetime" to Timestamp.now(),
//                "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
//                    Calendar.getInstance().get(Calendar.MONTH)
//                }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
//                "duedate" to duedate,
//
//
//                )
////                    viewModel.saveHabit(habit)
////                    finish()
//            Firebase.firestore.collection("goals").add(goal).addOnSuccessListener {
//                startActivity(Intent(this, Login::class.java))
//                finish()
//            }
//        } else {
//            Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT)
//                .show()
//        }
//        true
//
//        // else -> super.onOptionsItemSelected(item)
//    }
//}


    private fun setTimeListener() {
        myCalendar = Calendar.getInstance()

        timeSetListener =
            TimePickerDialog.OnTimeSetListener() { _: TimePicker, hourOfDay: Int, min: Int ->
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                myCalendar.set(Calendar.MINUTE, min)
                updateTime()
            }

        val timePickerDialog = TimePickerDialog(
            this, timeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE), false
        )
        timePickerDialog.show()
    }

    private fun updateTime() {
        //Mon, 5 Jan 2020
        val myformat = "h:mm a"
        val sdf = SimpleDateFormat(myformat)
        finalTime = myCalendar.time.time
        timeEdt.setText(sdf.format(myCalendar.time))

    }

    private fun setListener() {
        myCalendar = Calendar.getInstance()

        dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()

            }

        val datePickerDialog = DatePickerDialog(
            this, dateSetListener, myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    fun add_notification(view: View) {
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

    private fun updateDate() {
        //Mon, 5 Jan 2020
        val myformat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myformat)
        finalDate = myCalendar.time.time
        dateEdt.setText(sdf.format(myCalendar.time))

        timeInptLay.visibility = View.VISIBLE

    }

}