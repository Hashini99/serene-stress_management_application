package com.example.serene.daily_habits

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.Data
import androidx.work.ExistingWorkPolicy

import androidx.work.WorkManager
import com.example.serene.R
import com.example.serene.daily_habits.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Habit(
    val id : Int=0,
    val title: String="",

    val startTime:String,
    val priorityLevel:String,
    val minutesFocus:Int,
): Parcelable {

}


class CountDownActivity : AppCompatActivity() {
    //val db = Firebase.firestore
    private lateinit var oneTimeWorkRequest: OneTimeWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dailyhabit_countdown)

        supportActionBar?.title = "Count Down"

       val habit = intent.getParcelableExtra<Habit>(HABIT) as Habit

        findViewById<TextView>(R.id.tv_count_down_title).text = habit.title

        val viewModel = ViewModelProvider(this)[CountDownViewModel::class.java]

        //TODO 10 : Set initial time and observe current time. Update button state when countdown is finished
       // viewModel.setInitialTime(Habit.minutesFocus)

        viewModel.currentTimeString.observe(this, {
            findViewById<TextView>(R.id.tv_count_down).text = it
        })

        //TODO 13 : Start and cancel One Time Request WorkManager to notify when time is up.
        val channelName = getString(R.string.notify_channel_name)

        val workManager = WorkManager.getInstance(this)

        val data = Data.Builder()
            .putInt(HABIT_ID, habit.id)
            .putString(HABIT_TITLE, habit.title)
            .putString(NOTIFICATION_CHANNEL_ID, channelName)
            .build()

//        oneTimeWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
//            .setInputData(data)
//            .addTag(NOTIF_UNIQUE_WORK)
//            .build()

        viewModel.eventCountDownFinish.observe(this, {
            if (it) {
                workManager.enqueueUniqueWork(
                    NOTIF_UNIQUE_WORK,
                    ExistingWorkPolicy.REPLACE,
                    oneTimeWorkRequest
                )

                updateButtonState(false)
            }
        })


        findViewById<Button>(R.id.btn_start).setOnClickListener {
            updateButtonState(true)

            viewModel.startTimer()

        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            viewModel.resetTimer()

            updateButtonState(false)

            workManager.cancelUniqueWork(NOTIF_UNIQUE_WORK)
        }
    }

    private fun updateButtonState(isRunning: Boolean) {
        findViewById<Button>(R.id.btn_start).isEnabled = !isRunning
        findViewById<Button>(R.id.btn_stop).isEnabled = isRunning
    }
}

//class habit() : Parcelable {
//    val title: String,
//
//    constructor(parcel: Parcel) : this() {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<habit> {
//        override fun createFromParcel(parcel: Parcel): habit {
//            return habit(parcel)
//        }
//
//        override fun newArray(size: Int): Array<habit?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}

//class habit : Parcelable {
//    val habit = Habit(
//        title = title,
//        minutesFocus = minutesFocus,
//        startTime = startTime,
//        priorityLevel = priorityLevel
//    )
//
//}
