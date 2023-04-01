package com.example.serene.daily_habits

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.JournalMainActivity
import com.example.serene.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_daily_habit_single_view.*
import kotlinx.android.synthetic.main.activity_journal_single_view.*

class DailyHabitSingleView : AppCompatActivity() {

    lateinit var documentID: String
    lateinit var minutesFocus: String
    lateinit var startTime: String
    lateinit var habittitle: String
    //var client_id: String = "ARKL7p7RWGWudGaKC4KIjPhAd46IzO8Jl61jfgiEIMKxO3-JirB0te6vR-v_QFk9mYz1bAq00AJ9rqze"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_daily_habit_single_view)

        documentID = intent.getStringExtra("docID").toString()

        FirebaseFirestore.getInstance().collection("habits").document(documentID.toString()).get()
            .addOnSuccessListener {



                habittitle = it.data!!.getValue("title").toString()
                startTime = it.data!!.getValue("startTime").toString()
                minutesFocus = it.data!!.getValue("minutesFocus").toString()

                //txt_date.text = it.data!!.getValue("date").toString()
                txt_habitstart.text = it.data!!.getValue("minutesFocus").toString()
                txt_habittime.text = it.data!!.getValue("startTime").toString()
               //txt_habittitle = it.data!!.getValue("title").toString()
                txt_habittitle.text = it.data!!.getValue("title").toString()

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

    fun deleteOrder(view: View) {
        FirebaseFirestore.getInstance().collection("habits").document(documentID).delete()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Order Deleted Successful", Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, JournalMainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Cannot Delete the Order", Toast.LENGTH_LONG).show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DailyHabitsMain::class.java))
        finish()
    }
}