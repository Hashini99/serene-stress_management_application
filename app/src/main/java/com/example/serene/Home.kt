package com.example.serene

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.serene.academic_goal.AddGoal
import com.example.serene.chatbot.BotActivity
import com.example.serene.expert_support.ExpertSupportMain
import com.example.serene.history.HistoryMain
import com.example.serene.meditation.MeditationMain
import com.example.serene.montly_quiz.Quiz
import com.example.serene.montly_quiz.QuizIntro
import com.example.serene.moods.SelectMood
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main_task.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Home: AppCompatActivity() {
    val db = Firebase.firestore
    // declaring a null variable for VideoView
   // var simpleVideoView: VideoView? = null

    // declaring a null variable for MediaController
   // var mediaControls: MediaController? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_home)
        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {

            display_name.text = it.data!!.getValue("name").toString()

//            txt_phone.text = it.data!!.getValue("mobile").toString()
//            txt_address.text = it.data!!.getValue("address").toString()

        }.addOnFailureListener {
            Toast.makeText(applicationContext, "Cannot Get Data from Server", Toast.LENGTH_LONG)
                .show()
        }
//nav bar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_graph -> {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    true
                }
                R.id.main_graph -> {
                    val intent = Intent(this, MainTasks::class.java)
                    startActivity(intent)
                    true
                }
                R.id.charts_graph -> {
                    val intent = Intent(this, HistoryMain::class.java)
                    startActivity(intent)
                    true
                }
                R.id.expert_graph -> {
                    val intent = Intent(this, ExpertSupportMain::class.java)
                    startActivity(intent)
                    true
                }
                R.id.settings_graph -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }

        }

        mood_card.setOnClickListener {
            startActivity(Intent(this,SelectMood::class.java))
            finish()
        }
        //med
        mq_card.setOnClickListener {
            startActivity(Intent(this, QuizIntro::class.java))
            finish()
        }

//        btnSave.setOnClickListener {
//            lifecycleScope.launch {
//
//                val existingEntry = withContext(Dispatchers.IO) {
//                    GlobalData.userID?.let { userID ->
//                        GlobalData.dateInCalendar?.let { dateInCalendar ->
//                            userDao.readMoodOfUser(userID, dateInCalendar)
//                        }
//                    }
//                }




//        simpleVideoView = findViewById<View>(R.id.simpleVideoView) as VideoView
//
//        if (mediaControls == null) {
//            // creating an object of media controller class
//            mediaControls = MediaController(this)
//
//            // set the anchor view for the video view
//            mediaControls!!.setAnchorView(this.simpleVideoView)
//        }
//
//        // set the media controller for video view
//        simpleVideoView!!.setMediaController(mediaControls)
//
//        // set the absolute path of the video file which is going to be played
//        simpleVideoView!!.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.blue_sky}"))
//        // ("android.resource://$packageName/${R.raw.gfgvideo}")
//
//        simpleVideoView!!.requestFocus()
//
//        // starting the video
//        simpleVideoView!!.start()

        // display a toast message
        // after the video is completed
//        simpleVideoView!!.setOnCompletionListener {
//            Toast.makeText(applicationContext, "Video completed",
//                Toast.LENGTH_LONG).show()
//            true
//        }

        // display a toast message if any
        // error occurs while playing the video
//        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
//            Toast.makeText(applicationContext, "An Error Occurred " +
//                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
//            false
//        }




            }
    fun chat(view: View){
        startActivity(Intent(this,BotActivity ::class.java))
        finish()
    }
}





//package com.example.serene
//
//import android.app.*
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import android.widget.MediaController
//import android.widget.Toast
//import android.widget.VideoView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.NavController
//import androidx.navigation.findNavController
//import androidx.navigation.ui.NavigationUI
//import com.example.serene.academic_goal.AddGoal
//import com.example.serene.chatbot.BotActivity
//import com.example.serene.expert_support.ExpertSupportMain
//import com.example.serene.history.HistoryMain
//import com.example.serene.meditation.MeditationMain
//import com.example.serene.montly_quiz.Quiz
//import com.example.serene.montly_quiz.QuizIntro
//import com.example.serene.moods.SelectMood
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.messaging.FirebaseMessaging
//import kotlinx.android.synthetic.main.activity_home.*
//import kotlinx.android.synthetic.main.activity_main_task.*
//import kotlinx.android.synthetic.main.activity_profile.*
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.util.*
//
//class Home: AppCompatActivity() {
//    val db = Firebase.firestore
//    // declaring a null variable for VideoView
//    // var simpleVideoView: VideoView? = null
//
//    // declaring a null variable for MediaController
//    // var mediaControls: MediaController? = null
//
//
//
//    private lateinit var navController: NavController
//    private val CHANNEL_ID = "NotificationChannelID"
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//        }
//        setContentView(R.layout.activity_home)
//
//
//        // Create notification channel for Android 8.0+
//        createNotificationChannel()
//
//        // Initialize Firebase Messaging and subscribe to topic
//        FirebaseMessaging.getInstance().subscribeToTopic("allDevices")
//
////        navController = findNavController(R.id.nav_host_fragment)
////        NavigationUI.setupWithNavController(nav_view, navController)
//
//        // Set up alarm manager to send notification at selected time
//        setAlarmManager()
//
//
//        FirebaseFirestore.getInstance().collection("users")
//            .document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
//
//                display_name.text = it.data!!.getValue("name").toString()
//
////            txt_phone.text = it.data!!.getValue("mobile").toString()
////            txt_address.text = it.data!!.getValue("address").toString()
//
//            }.addOnFailureListener {
//                Toast.makeText(applicationContext, "Cannot Get Data from Server", Toast.LENGTH_LONG)
//                    .show()
//            }
////nav bar
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.home_graph -> {
//                    val intent = Intent(this, Home::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.main_graph -> {
//                    val intent = Intent(this, MainTasks::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.charts_graph -> {
//                    val intent = Intent(this, HistoryMain::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.expert_graph -> {
//                    val intent = Intent(this, ExpertSupportMain::class.java)
//                    startActivity(intent)
//                    true
//                }
//                R.id.settings_graph -> {
//                    val intent = Intent(this, Profile::class.java)
//                    startActivity(intent)
//                    true
//                }
//                else -> false
//            }
//
//        }
//
//        mood_card.setOnClickListener {
//            startActivity(Intent(this, SelectMood::class.java))
//            finish()
//        }
//        //med
//        mq_card.setOnClickListener {
//            startActivity(Intent(this, QuizIntro::class.java))
//            finish()
//        }
//    }
//
//
//
//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Notification Channel"
//            val descriptionText = "Notification Channel Description"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//    private fun setAlarmManager() {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, 12)
//        calendar.set(Calendar.MINUTE, 0)
//        calendar.set(Calendar.SECOND, 0)
//        val intent = Intent(this, AlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY, pendingIntent
//        )
//    }
//
//    private fun sendNotification() {
////        val intent = Intent(this, HomeActivity::class.java).apply {
////            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////        }
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
//
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.cancle)
//            .setContentTitle("It's time to check")
//            .setContentText("Don't forget to check on your tasks for the day!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//
////        with(NotificationManagerCompat.from(this)) {
////            // notificationId is a unique int for each notification that you must define
////            notify(1, builder.build())
////        }
//    }
//
//    inner class AlarmReceiver : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            // Call sendNotification to send push notification to user
//            sendNotification()
//        }
//    }
//
//
//
//    fun chat(view: View){
//        startActivity(Intent(this,BotActivity ::class.java))
//        finish()
//    }
//}