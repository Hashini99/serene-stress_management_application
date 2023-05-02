package com.example.serene

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_journal_single_view.*

import android.view.View






class JournalSingleView: AppCompatActivity() {

    lateinit var documentID: String
    lateinit var decription: String
    lateinit var title: String
    //var client_id: String = "ARKL7p7RWGWudGaKC4KIjPhAd46IzO8Jl61jfgiEIMKxO3-JirB0te6vR-v_QFk9mYz1bAq00AJ9rqze"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_journal_single_view)

        documentID = intent.getStringExtra("docID").toString()

        FirebaseFirestore.getInstance().collection("journal").document(documentID.toString()).get()
            .addOnSuccessListener {



        title = it.data!!.getValue("title").toString()
        decription = it.data!!.getValue("description").toString()


        txt_date.text = it.data!!.getValue("date").toString()
        txt_time.text = it.data!!.getValue("time").toString()
        txt_title.text = it.data!!.getValue("title").toString()
        txt_description.text = it.data!!.getValue("description").toString()

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

    fun delete_journal(view: View) {
        FirebaseFirestore.getInstance().collection("journal").document(documentID).delete()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Journal Deleted Successful", Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, JournalMainActivity::class.java))
                finish()
            }.addOnFailureListener {
            Toast.makeText(applicationContext, "Cannot Delete the Journal", Toast.LENGTH_LONG).show()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, JournalMainActivity::class.java))
        finish()
    }
}