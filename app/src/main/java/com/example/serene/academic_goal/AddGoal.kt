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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_acdemic_goal_adding.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AddGoal : AppCompatActivity() , View.OnClickListener{


    lateinit var myCalendar: Calendar

    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var startDate = 0L
    var endDate = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acdemic_goal_adding)

        supportActionBar?.title = getString(R.string.add_habit)
      start_date.setOnClickListener(this)
        end_date.setOnClickListener(this)
       saveBtn.setOnClickListener(this)


    }
//
     override fun onClick(v: View) {
    when (v.id) {
        R.id.start_date -> {
            setStartDateListener()
        }
//        R.id.end_date -> {
//            setEndDateListener()
//        }
    }

}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_save -> {
//                val title = findViewById<EditText>(R.id.titleInpLay).text.toString()
//
//                val tasks = findViewById<EditText>(R.id.taskInpLay).text.toString()
//
//                val startDate = findViewById<TextView>(R.id.start_date).text.toString()
              //  val endDate = findViewById<TextView>(R.id.end_date).text.toString()


fun save_goal(view: View){
                if (title.isNotEmpty()) {
                    val title = findViewById<EditText>(R.id.titleInpLay).text.toString()

                    val tasks = findViewById<EditText>(R.id.taskInpLay).text.toString()

                    val startDate = findViewById<TextView>(R.id.start_date).text.toString()
                      val endDate = findViewById<TextView>(R.id.end_date).text.toString()


                    val goal = hashMapOf(
                        "user" to FirebaseAuth.getInstance().currentUser?.uid,
                        "title" to title,
                       "tasks" to tasks,
                        //"startTime" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}",
                        "startDate" to startDate,
                        "endDate" to endDate
                    )
//                    viewModel.saveHabit(habit)
//                    finish()
                    Firebase.firestore.collection("goals").add(goal).addOnSuccessListener {
                        startActivity(Intent(this, CreateAccount::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT)
                        .show()
                }

            }






    private fun setStartDateListener() {
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

//    private fun setEndDateListener() {
//        myCalendar = Calendar.getInstance()
//
//        dateSetListener =
//            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
//                myCalendar.set(Calendar.YEAR, year)
//                myCalendar.set(Calendar.MONTH, month)
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                updateDate()
//
//            }
//        val datePickerDialog = DatePickerDialog(
//            this, dateSetListener, myCalendar.get(Calendar.YEAR),
//            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)
//        )
//        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
//        datePickerDialog.show()
//    }


    private fun updateDate() {
        //Mon, 5 Jan 2020
        val myformat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myformat)
        startDate = myCalendar.time.time
        endDate = myCalendar.time.time
        start_date.setText(sdf.format(myCalendar.time))
        end_date.setText(sdf.format(myCalendar.time))

//        timeInptLay.visibility = View.VISIBLE
    }
}