package com.example.serene


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class CreateAccount : AppCompatActivity(){
    private lateinit var  auth: FirebaseAuth
    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etRepeatPassword: EditText

    val MIN_PASSWORD_LENGTH = 6;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
      auth= FirebaseAuth.getInstance()

        viewInitializations()

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }

    fun viewInitializations() {
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_Email)
        etPassword = findViewById(R.id.et_password)
        etRepeatPassword = findViewById(R.id.et_repeat_password)




        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun validateInput(): Boolean {
        if (etName.text.toString().equals("")) {
            etName.setError("Please Enter First Name")
            return false
        }

        if (etEmail.text.toString().equals("")) {
            etEmail.setError("Please Enter Email")
            return false
        }
        if (etPassword.text.toString().equals("")) {
            etPassword.setError("Please Enter Password")
            return false
        }
        if (etRepeatPassword.text.toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.setError("Please Enter Valid Email")
            return false
        }



        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters")
            return false
        }

        // Checking if repeat password is same
        if (!etPassword.text.toString().equals(etRepeatPassword.text.toString())) {
            etRepeatPassword.setError("Password does not match")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun register(view: View) {
        if (validateInput()) {

            // Input is valid, here send data to server

            val Name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()


            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser!!.uid

                    val user = hashMapOf(
                        "name" to Name,
                        "email" to email,

                    )

                    Firebase.firestore.collection("users").document(uid).set(user).addOnSuccessListener {
                        startActivity(Intent(this,Login::class.java))
                        finish()
                    }

                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

            fun goToLogin(view: View) {
                startActivity(Intent(this, Login::class.java))
           finish()
            }
//    fun goToLogin(savedInstanceState: Bundle?) {
////                startActivity(Intent(this, Login::class.java))
////                finish()
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//    }

           override fun onBackPressed() {
               startActivity(Intent(this, Login::class.java))
               finish()
           }}

// this is test