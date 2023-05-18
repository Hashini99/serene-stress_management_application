package com.example.serene

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*



//class Login: AppCompatActivity() {
//   private lateinit var  auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        title="Login"
//        auth= FirebaseAuth.getInstance()
//
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {}
//    }
//
//    fun login(view: View){
//        val email=editTextEmail.text.toString()
//        val password=editTextPassword.text.toString()
//        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                val intent= Intent(this,Home::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }.addOnFailureListener { exception ->
//            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
//        }
//    }
//
//    fun goToRegister(view: View){
//        startActivity(Intent(this, CreateAccount::class.java))
//        finish()
//    }
//
//    fun goToForgetPassword(view: View){
//        startActivity(Intent(this, ForgetPassword::class.java))
//        finish()
//    }
//}

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Login"
        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("login_status", Context.MODE_PRIVATE)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is already logged in, redirect to Home activity
            navigateToHome()
        }
    }

    fun login(view: View) {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Login successful, store login status and navigate to Home activity
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                navigateToHome()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun goToRegister(view: View) {
        startActivity(Intent(this, CreateAccount::class.java))
        finish()
    }

    fun goToForgetPassword(view: View) {
        startActivity(Intent(this, ForgetPassword::class.java))
        finish()
    }

    private fun navigateToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}


