package com.example.serene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.serene.academic_goal.AddGoal
import com.example.serene.academic_goal.GoalMain
import com.example.serene.chatbot.BotActivity
import com.example.serene.daily_habits.AddDailyHabits
import com.example.serene.daily_habits.DailyHabitsMain
import com.example.serene.expert_support.ChannelList
import com.example.serene.expert_support.Channeling
//import com.example.serene.daily_habits.Habit
import com.example.serene.expert_support.VideoChat
import com.example.serene.history.MoodHistory
import com.example.serene.history.StressTrackerOverview
import com.example.serene.meditation.MeditationMain
import com.example.serene.montly_quiz.Quiz
import com.example.serene.moods.SelectMood
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*



class Login: AppCompatActivity() {
   private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title="Login"
        auth= FirebaseAuth.getInstance()

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }

    fun login(view: View){
        val email=editTextEmail.text.toString()
        val password=editTextPassword.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,Home::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun goToRegister(view: View){
        startActivity(Intent(this, CreateAccount::class.java))
        finish()
    }

    fun goToForgetPassword(view: View){
        startActivity(Intent(this, ForgetPassword::class.java))
        finish()
    }
}