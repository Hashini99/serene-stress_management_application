package com.example.serene

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_journal_single_view.*



import android.annotation.SuppressLint
import android.app.Activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask


import android.util.Log
import android.view.View
import android.widget.ImageView

import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


import java.math.BigDecimal
import java.text.NumberFormat





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

//    fun validate():Boolean{
//        if (txt_description.text.toString().equals("")) {
//            txt_description.setError("Description Cannot be Empty")
//            return false
////        }else if (etAddress.text.toString().equals("")) {
////            etAddress.setError("Address Cannot be Empty")
////            return false
////        }else if (etPhoneNumber.text.toString().equals("")) {
////            etPhoneNumber.setError("Phone Number Cannot be Empty")
////            return false
//        }else{
//            return true
//        }
//    }
//    fun update_journal(view: View){
//        if(validate()){
//            val description = txt_description.text.toString()
////            val address = etAddress.text.toString()
////            val mobile = etPhoneNumber.text.toString()
//
//            val user = hashMapOf(
//                "description" to description,
////                "address" to address,
////                "mobile" to mobile,
//                "email" to FirebaseAuth.getInstance().currentUser!!.email
//            )
//
//            Firebase.firestore.collection("journal").document(FirebaseAuth.getInstance().currentUser!!.uid).set(user).addOnSuccessListener {
//                Toast.makeText(applicationContext,"Updated Successful", Toast.LENGTH_LONG).show()
//                startActivity(Intent(this, Home::class.java))
//                finish()
//            }.addOnFailureListener{
//                Toast.makeText(applicationContext,"Cannot Update", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, JournalMainActivity::class.java))
        finish()
    }
}