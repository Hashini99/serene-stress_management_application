package com.example.serene
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.daily_habits.documentID
import com.example.serene.expert_support.ExpertSupportMain
import com.example.serene.history.HistoryMain
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_profile.*

class Profile: AppCompatActivity()
//{
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        supportActionBar?.title = getString(R.string.pr)
//
//        setContentView(R.layout.activity_profile)
//
//
//
//        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
//
//            txt_name.text = it.data!!.getValue("name").toString()
//            txt_email.text = it.data!!.getValue("email").toString()
////            txt_phone.text = it.data!!.getValue("mobile").toString()
////            txt_address.text = it.data!!.getValue("address").toString()
//
//        }.addOnFailureListener{
//            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
//
//            startActivity(Intent(this, Profile::class.java))
//            finish()
//        }
//        signout.setOnClickListener(View.OnClickListener {
////            firebaseAuth.getInstance().signOut()
//            FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().signOut()
//                .toString())
//            val intent = Intent(this, Login::class.java)
//            startActivity(intent)
//        })
//
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navpr)
//
//
//
////        // Create a ColorStateList object from the selector XML file
////        val colorStateList = resources.getColorStateList(R.color.bottom_nav_colors, null)
////
////// Set the color of the selected item to the ColorStateList
////        bottomNavigationView.itemTextColor = colorStateList
////        bottomNavigationView.itemIconTintList = colorStateList
////
//
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.home_graph -> {
//                    val intent = Intent(this, Home::class.java)
//                    startActivity(intent)
//                  //  bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
//                R.id.main_graph -> {
//                    val intent = Intent(this, MainTasks::class.java)
//                    startActivity(intent)
//                   // bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
//                R.id.charts_graph -> {
//                    val intent = Intent(this, HistoryMain::class.java)
//                    startActivity(intent)
//                   // bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
//                R.id.expert_graph -> {
//                    val intent = Intent(this, ExpertSupportMain::class.java)
//                    startActivity(intent)
//                  //  bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }
////                R.id.settings_graph -> {
////                    val intent = Intent(this, Profile::class.java)
////                    startActivity(intent)
////                 //   bottomNavigationView.selectedItemId = menuItem.itemId
////                    true
////                }
//
//                R.id.settings_graph -> {
//                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
//                        // Do nothing if the selected item is already the profile page
//                        return@setOnItemSelectedListener true
//                    } else {
//                        val intent = Intent(this, Profile::class.java)
//                        startActivity(intent)
//                        bottomNavigationView.selectedItemId = menuItem.itemId
//                        true
//                    }
//                }
//                else -> false
//            }
//        }
//    }
//
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this,Home::class.java))
//        finish()
//    }
//
//    fun goToUpdateProfile(view: View){
//        startActivity(Intent(this, UpdateProfile::class.java))
//        finish()
//    }
//    fun DeleteAccount(view: View) {
//        FirebaseFirestore.getInstance().collection("users").document(documentID).delete()
//            .addOnSuccessListener {
//                Toast.makeText(applicationContext, "Account Deleted Successful", Toast.LENGTH_LONG)
//                    .show()
//                startActivity(Intent(this, CreateAccount::class.java))
//                finish()
//            }.addOnFailureListener {
//                Toast.makeText(applicationContext, "Cannot Delete the Account", Toast.LENGTH_LONG).show()
//            }
//    }
//    fun goTosignout(view: View){
//        FirebaseAuth.getInstance().signOut();
//
//    }
//    }
//
////    fun goTosignout(view: View){
////        FirebaseAuth.getInstance().signOut();
////
////    }


{

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.pr)

        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("login_status", Context.MODE_PRIVATE)

        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {

            txt_name.text = it.data!!.getValue("name").toString()
            txt_email.text = it.data!!.getValue("email").toString()
//            txt_phone.text = it.data!!.getValue("mobile").toString()
//            txt_address.text = it.data!!.getValue("address").toString()

        }.addOnFailureListener{
            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()

            startActivity(Intent(this, Profile::class.java))
            finish()
        }
        signout.setOnClickListener(View.OnClickListener {
//            firebaseAuth.getInstance().signOut()
            FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().signOut()
                .toString())
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        })


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navpr)
        bottomNavigationView.selectedItemId = R.id.settings_graph



        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_graph -> {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    //  bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.main_graph -> {
                    val intent = Intent(this, MainTasks::class.java)
                    startActivity(intent)
                    // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.charts_graph -> {
                    val intent = Intent(this, HistoryMain::class.java)
                    startActivity(intent)
                    // bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
                R.id.expert_graph -> {
                    val intent = Intent(this, ExpertSupportMain::class.java)
                    startActivity(intent)
                    //  bottomNavigationView.selectedItemId = menuItem.itemId
                    true
                }
//                R.id.settings_graph -> {
//                    val intent = Intent(this, Profile::class.java)
//                    startActivity(intent)
//                 //   bottomNavigationView.selectedItemId = menuItem.itemId
//                    true
//                }

                R.id.settings_graph -> {
                    if (menuItem.itemId == bottomNavigationView.selectedItemId) {
                        // Do nothing if the selected item is already the profile page
                        return@setOnItemSelectedListener true
                    } else {
                        val intent = Intent(this, Profile::class.java)
                        startActivity(intent)
                        bottomNavigationView.selectedItemId = menuItem.itemId
                        true
                    }
                }
                else -> false
            }
        }
        val signOutButton: Button = findViewById(R.id.signout)
        signOutButton.setOnClickListener {
            auth.signOut()
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            navigateToLogin()
        }

        val deleteButton: Button = findViewById(R.id.deleteA)
        deleteButton.setOnClickListener {
            deleteAccount()
        }
    }
    private fun navigateToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,Home::class.java))
        finish()
    }

    fun goToUpdateProfile(view: View){
        startActivity(Intent(this, UpdateProfile::class.java))
        finish()
    }

    fun goTosignout(view: View){
        FirebaseAuth.getInstance().signOut();

    }

    private fun deleteAccount() {
        val user = FirebaseAuth.getInstance().currentUser

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Are you sure you want to delete your account?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                user?.delete()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Account deletion successful
                        Toast.makeText(this, "Account deleted successfully", Toast.LENGTH_SHORT).show()

                        navigateToGoodbye()
                    } else {
                        // Account deletion failed
                        Toast.makeText(this, "Failed to delete account", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Confirmation")
        alert.show()
    }
    private fun navigateToGoodbye() {
        val intent = Intent(this, CreateAccount::class.java)
        startActivity(intent)
        finish()
    }
}

//    fun goTosignout(view: View){
//        FirebaseAuth.getInstance().signOut();
//
//    }