package com.example.serene.expert_support

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.CreateAccount
import com.example.serene.JournalMainActivity
import com.example.serene.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_channel_single_view.*
import kotlinx.android.synthetic.main.activity_journal_single_view.*
import kotlinx.android.synthetic.main.activity_journal_single_view.txt_title
import java.util.*

class ChannelSingleView: AppCompatActivity() {

    lateinit var documentID: String
    lateinit var expert: String
    lateinit var date: String
    lateinit var time: String
    //var client_id: String = "ARKL7p7RWGWudGaKC4KIjPhAd46IzO8Jl61jfgiEIMKxO3-JirB0te6vR-v_QFk9mYz1bAq00AJ9rqze"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_channel_single_view)

        documentID = intent.getStringExtra("docID").toString()

        FirebaseFirestore.getInstance().collection("bookings").document(documentID.toString()).get()
            .addOnSuccessListener {



                expert = it.data!!.getValue("counsellor").toString()
                date = it.data!!.getValue("ChannelDate").toString()
               time = it.data!!.getValue("time").toString()


                txt_a_date.text = it.data!!.getValue("ChannelDate").toString()
                txt_a_time.text = it.data!!.getValue("time").toString()
                txt_d_name.text = it.data!!.getValue("counsellor").toString()
                //txt_description.text = it.data!!.getValue("description").toString()

                //descriptionVisibility()

            }.addOnFailureListener{
                Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
            }

    }

    override fun onDestroy() {
        //stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }
//    fun descriptionVisibility(){
//        if(decription == "" || decription == null){
//            txt_description.setVisibility(View.GONE)
//            lbl_description.setVisibility(View.GONE)
//        }
//    }

    fun delete_app(view: View) {
        FirebaseFirestore.getInstance().collection("bookings").document(documentID).delete()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Appointment cancel Successful", Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, ChannelList::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Cannot Delete the Appointment", Toast.LENGTH_LONG).show()
            }
    }
    fun reminderr(view: View) {
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
    fun meetExpert(view: View){
        startActivity(Intent(this,VideoChat ::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ChannelList::class.java))
        finish()
    }
}