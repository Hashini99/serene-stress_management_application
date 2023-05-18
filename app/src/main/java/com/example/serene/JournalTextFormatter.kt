package com.example.serene

import android.content.ClipDescription
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.serene.daily_habits.documentID
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_update_journal.*
import kotlinx.android.synthetic.main.activity_update_profile.*
lateinit var documentID: String
class JournalTextFormatter: AppCompatActivity() {

    lateinit var ettitle: EditText
    lateinit var etdes: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_update_journal)

        ettitle = findViewById(R.id.et_title)
       etdes = findViewById(R.id.et_des)


       // FirebaseFirestore.getInstance().collection("journal").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
            FirebaseFirestore.getInstance().collection("journal").document(documentID.toString()).get()     .addOnSuccessListener {


            et_title.setText(it.data!!.getValue("title").toString())
            et_des.setText(it.data!!.getValue("description").toString())


        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, Profile::class.java))
            finish()
        }
    }

    fun validate():Boolean{
        if (etdes.text.toString().equals("")) {
            etdes.setError("Name Cannot be Empty")
            return false

        }else{
            return true
        }
    }

    fun updateJournalData(view: View){
        if(validate()){
            val description = etdes.text.toString()
           val title = ettitle.text.toString()
//            val mobile = etPhoneNumber.text.toString()

            val journal = hashMapOf(
                "title" to title,
                "description" to description,

                "email" to FirebaseAuth.getInstance().currentUser!!.email
            )

            Firebase.firestore.collection("journal").document(FirebaseAuth.getInstance().currentUser!!.uid).set(journal).addOnSuccessListener {
                Toast.makeText(applicationContext,"Updated Successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Home::class.java))
                finish()
            }.addOnFailureListener{
                Toast.makeText(applicationContext,"Cannot Update", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, Profile::class.java))
        finish()
    }
}