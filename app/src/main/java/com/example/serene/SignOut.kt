package com.example.serene

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignOut: AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//        }
//        //Declaration and defination
//        private FirebaseAuth firebaseAuth;
//        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser() == null){
//                    //Do anything here which needs to be done after signout is complete
//                    signOutComplete();
//                }
//                else {
//                }
//            }
//        };
//
////Init and attach
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.addAuthStateListener(authStateListener);
//
////Call signOut()
//        firebaseAuth.signOut()
//    }




        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            try {
                this.supportActionBar!!.hide()
            } catch (e: NullPointerException) {
            }

            auth = FirebaseAuth.getInstance()

            // Call signOut() to sign out the current user
            auth.signOut()

            // Do anything here which needs to be done after signout is complete
            signOutComplete()

            // Finish the SignOut activity
            finish()
        }

        private fun signOutComplete() {
            // Add your code here to perform any action after sign out is complete
        }
    }

