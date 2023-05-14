package com.example.serene.expert_support

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.CreateAccount
import com.example.serene.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_expert_channel.*
import java.text.SimpleDateFormat
import java.util.*

class Channeling : AppCompatActivity(), View.OnClickListener {

    lateinit var myCalendar: Calendar

    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener

    var finalDate = 0L
    var finalTime = 0L




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert_channel)
        supportActionBar?.title = getString(R.string.a_b)
        add_date.setOnClickListener(this)
        add_time.setOnClickListener(this)
        Channel_done.setOnClickListener(this)



    }



    override fun onClick(v: View) {
        when (v.id) {
            R.id.add_date -> {
                setListener()
            }
            R.id.add_time -> {
                setTimeListener()
            }
            R.id.Channel_done -> {
                saveTodo()
            }
        }

    }

    private fun saveTodo() {




        val tel = input_tp.editText?.text.toString()
        val channelDate = findViewById<TextView>(R.id.add_date).text.toString()
        val docD = findViewById<Spinner>(R.id.docD).selectedItem.toString()
        val time = findViewById<TextView>(R.id.add_time).text.toString()

        if (docD.isNotEmpty()) {
            val channel = hashMapOf(
                "user" to FirebaseAuth.getInstance().currentUser?.uid,
                //"doctor" to name,
                "tel" to tel,
                "counsellor" to docD,

                "datetime" to Timestamp.now(),
                "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${
                    Calendar.getInstance().get(Calendar.MONTH)
                }-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",

                "ChannelDate" to channelDate,
                "time" to time,


            )

            Firebase.firestore.collection("bookings").add(channel).addOnSuccessListener {
                startActivity(Intent(this,ChannelList::class.java))
                finish()
            }
        }else {
            Toast.makeText(this, getString(R.string.empty_message), Toast.LENGTH_SHORT)
                .show()
        }
    }
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
        add_time.setText(sdf.format(myCalendar.time))

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
        //Mon, 5 Jan 2022
        val myformat = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(myformat)
        finalDate = myCalendar.time.time
        add_date.setText(sdf.format(myCalendar.time))

        //timeInpt.visibility = View.VISIBLE

    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ChannelList::class.java))
        finish()
    }

}