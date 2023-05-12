package com.example.serene.daily_habits

//import android.os.Parcel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.serene.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dailyhabit_countdown.*
import kotlinx.android.synthetic.main.dailyhabit_single_view.*
import kotlin.properties.Delegates

//@Parcelize
//data class Habit(
//    val id : Int=0,
//    val title: String="",
//
//    val startTime:String,
//    val priorityLevel:String,
//    val minutesFocus:Int,
//): Parcelable {

//}


//class CountDownActivity : AppCompatActivity() {
//
//    lateinit var documentID: String
//    lateinit var minutesFocus: String
//    lateinit var startTime: String
//    lateinit var habittitle: String
//
//    val db = Firebase.firestore
//    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_dailyhabit_countdown)
//
//        supportActionBar?.title = "Count Down"
//
//
//
//    //   val habit = intent.getParcelableExtra<Habit>(HABIT) as Habit
//
//        findViewById<TextView>(R.id.tv_count_down_title).text = DailyHabitSingleView.habittitle
//
//        val viewModel = ViewModelProvider(this)[CountDownViewModel::class.java]
//
//        //TODO 10 : Set initial time and observe current time. Update button state when countdown is finished
//       // viewModel.setInitialTime(Habit.minutesFocus)
//
//        viewModel.currentTimeString.observe(this, {
//            findViewById<TextView>(R.id.tv_count_down).text = it
//        })
//
//        //TODO 13 : Start and cancel One Time Request WorkManager to notify when time is up.
//        val channelName = getString(R.string.notify_channel_name)
//
//        val workManager = WorkManager.getInstance(this)
//
//        val data = Data.Builder()
//            .putInt(HABIT_ID, DailyHabitSingleView.id)
//            .putString(HABIT_TITLE, DailyHabitSingleView.title)
//            .putString(NOTIFICATION_CHANNEL_ID, channelName)
//            .build()
//
////        oneTimeWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
////            .setInputData(data)
////            .addTag(NOTIF_UNIQUE_WORK)
////            .build()
//
//        viewModel.eventCountDownFinish.observe(this, {
//            if (it) {
//                workManager.enqueueUniqueWork(
//                    NOTIF_UNIQUE_WORK,
//                    ExistingWorkPolicy.REPLACE,
//                    oneTimeWorkRequest
//                )
//
//                updateButtonState(false)
//            }
//        })
//
//
//        findViewById<Button>(R.id.btn_start).setOnClickListener {
//            updateButtonState(true)
//
//            viewModel.startTimer()
//
//        }
//
//        findViewById<Button>(R.id.btn_stop).setOnClickListener {
//            viewModel.resetTimer()
//
//            updateButtonState(false)
//
//            workManager.cancelUniqueWork(NOTIF_UNIQUE_WORK)
//        }
//    }
//
//    private fun updateButtonState(isRunning: Boolean) {
//        findViewById<Button>(R.id.btn_start).isEnabled = !isRunning
//        findViewById<Button>(R.id.btn_stop).isEnabled = isRunning
//    }
//}
//@Parcelize
//data class Habit(
//    val id : Int=0,
//    val title: String="",
//
//    val startTime:String,
//    val priorityLevel:String,
//    val minutesFocus:Int,
//): Parcelable {
//
//}


var habitN: TextView? = null


class CountDownActivity : AppCompatActivity() {


//    lateinit var documentID: String
//    var minutesFocuss by Delegates.notNull<Double>()
//    lateinit var startTime: String
//    lateinit var habititle: String
//    val db = Firebase.firestore
//    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_dailyhabit_countdown)
//
//        supportActionBar?.title = "Count Down"
//
//        //setContentView(R.layout.dailyhabit_single_view)
//
//        documentID = intent.getStringExtra("docID").toString()
//        val viewModel = ViewModelProvider(this)[CountDownViewModel::class.java]
//        val workManager = WorkManager.getInstance(this)
//        FirebaseFirestore.getInstance().collection("habits").document(documentID.toString()).get()
//            .addOnSuccessListener {
//
//
//       //        habittitle = it.data!!.getValue("title").toString()
//
//               // minutesFocus = it.data!!.getValue("minutesFocus").toString()
//
//                //txt_date.text = it.data!!.getValue("date").toString()
////                tv_count_down.text = it.data!!.getValue("minutesFocus").toString()
//               // sttime.text = it.data!!.getValue("startTime").toString()
//          //      tv_count_down_title.text = it.data!!.getValue("title").toString()
//          //      tv_count_down_title.text = it.data!!.getValue("title").toString()
//
//
//               // tv_count_down_title.text=habitN.text
//
//              //  findViewById<TextView>(R.id.tv_count_down_title).text = findViewById<TextView>(R.id.habitN).text
//
//                //findViewById<TextView>(R.id.tv_count_down_title).text =habitN.text
////               val habitN: TextView? = findViewById(R.id.tv_count_down_title)
////                val text: CharSequence? = habitN?.text
//
//
//                val title = it.data?.getValue("title")?.toString()
//                if (title != null) {
//                    tv_count_down_title.text = title
//                }
//                val minF=it.data?.getValue("minutesFocus")?.toString()
//                if (minF != null) {
//                    tv_count_down.text = title
//                }
//
////                habititle = it.data!!.getValue("title").toString()
////             //   startTime = it.data!!.getValue("startTime").toString()
////                minutesFocuss = it.data!!.getValue("minutesFocus").toString().toDouble()
////
////                //txt_date.text = it.data!!.getValue("date").toString()
////                tv_count_down.text = it.data!!.getValue("minutesFocus").toString()
////                //sttime.text = it.data!!.getValue("startTime").toString()
////                //txt_habittitle = it.data!!.getValue("title").toString()
////                tv_count_down_title.text = it.data!!.getValue("title").toString()
//            }
//
//        findViewById<Button>(R.id.btn_start).setOnClickListener {
//            updateButtonState(true)
//
//            viewModel.startTimer()
//
//        }
//
//        findViewById<Button>(R.id.btn_stop).setOnClickListener {
//            viewModel.resetTimer()
//
//            updateButtonState(false)
//
//            workManager.cancelUniqueWork(NOTIF_UNIQUE_WORK)
//        }
//    }
//
//    private fun updateButtonState(isRunning: Boolean) {
//        findViewById<Button>(R.id.btn_start).isEnabled = !isRunning
//        findViewById<Button>(R.id.btn_stop).isEnabled = isRunning
//    }
}