package com.example.serene

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfile : AppCompatActivity() {

    lateinit var etName: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}

        setContentView(R.layout.activity_update_profile)

        etName = findViewById(R.id.et_name)


        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {

            et_name.setText(it.data!!.getValue("name").toString())


        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, Profile::class.java))
            finish()
        }
    }

    fun validate():Boolean{
        if (etName.text.toString().equals("")) {
            etName.setError("Name Cannot be Empty")
            return false

        }else{
            return true
        }
    }

    fun updateUserData(view: View){
        if(validate()){
            val name = etName.text.toString()


            val user = hashMapOf(
                "name" to name,

                "email" to FirebaseAuth.getInstance().currentUser!!.email
            )

            Firebase.firestore.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(user).addOnSuccessListener {
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