package com.example.serene.expert_support

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.JournalMainActivity
import com.example.serene.JournalViewHolder

import com.example.serene.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_journal_single_view.*
import kotlinx.android.synthetic.main.expert_single_view.*

class ExpertSingleView : AppCompatActivity() {

    lateinit var documentID: String
    lateinit var decription: String
    lateinit var name: String
    lateinit var available: String
    //var client_id: String = "ARKL7p7RWGWudGaKC4KIjPhAd46IzO8Jl61jfgiEIMKxO3-JirB0te6vR-v_QFk9mYz1bAq00AJ9rqze"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.expert_single_view)

        documentID = intent.getStringExtra("docID").toString()

        FirebaseFirestore.getInstance().collection("expert").document(documentID.toString()).get()
            .addOnSuccessListener {



                name = it.data!!.getValue("name").toString()
                decription = it.data!!.getValue("qulification").toString()
               available = it.data!!.getValue("available").toString()

//                txt_date.text = it.data!!.getValue("date").toString()
//                txt_time.text = it.data!!.getValue("time").toString()
                txt_name.text = it.data!!.getValue("name").toString()
                desc.text = it.data!!.getValue("qulification").toString()
                textView8.text = it.data!!.getValue("available").toString()

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



    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ExpertList::class.java))
        finish()
    }
}