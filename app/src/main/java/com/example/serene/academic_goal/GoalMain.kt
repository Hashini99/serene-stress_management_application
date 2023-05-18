

package com.example.serene.academic_goal

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.serene.MainTasks
import com.example.serene.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_acdemic_goal_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import androidx.work.*
//import androidx.work.ConstraintsBuilder
import androidx.work.Constraints
//import com.example.serene.daily_habits.SocialHabitDocument
import com.google.firebase.Timestamp


data class Goal(

    val title: String = "",
    val tasks:String="",
    val duedate:String="",

    val startTime:String="",
    val date:String="",
    val user:String="",
    val datetime: Timestamp? = null

//    var date: String = "",  // Add a date property
//    var datetime:Date,  // Add a datetime property
  // var user: String =""
)




const val GOAL_NOTIFICATION_CHANNEL_ID = "my_channel_id"
private const val GOAL_NOTIFICATION_ID = 1


//class GoalMainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
//
//{
//    init {
//        itemView.setOnClickListener {
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                val goal = getItem(position)
//                val documentId = snapshots.getSnapshot(position).id
//                showEditGoalDialog(goal, documentId)
//            }
//        }
//    }
//}


class GoalMainViewHolder(
    itemView: View,
    getItem: (Int) -> Goal,
    showEditGoalDialog: (Goal, String) -> Unit,
    private val documentIdGetter: (Int) -> String
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val goal = getItem(position)
                val documentId = documentIdGetter(position)
                showEditGoalDialog(goal, documentId)
            }
        }
    }
}

class GoalMain : AppCompatActivity() {



    val db = Firebase.firestore

    companion object {
        private const val GOAL_NOTIFICATION_WORKER_TAG = "GOAL_NOTIFICATION_WORKER_TAG"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acdemic_goal_main)

        supportActionBar?.title = getString(R.string.a_gm)

        // Create notification channel (required for Android O and above)
        createNotificationChannel()

        // Schedule the GoalNotificationWorker to run at 12:00pm every day
        scheduleGoalNotificationWorker()


        val query = db.collection("goal")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
            //.orderBy("datetime", Query.Direction.DESCENDING)
            .orderBy("user")
            .orderBy("datetime", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Goal>().setQuery(query, Goal::class.java)
            .setLifecycleOwner(this).build()


//        val adapter = object : FirestoreRecyclerAdapter<Goal, GoalMainViewHolder>(options) {
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalMainViewHolder {
//                val view = LayoutInflater.from(this@GoalMain)
//                    .inflate(R.layout.acedimic_goal_list, parent, false)
//                return GoalMainViewHolder(view, ::getItem, ::showEditGoalDialog)
//
//            }
        val adapter = object : FirestoreRecyclerAdapter<Goal, GoalMainViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalMainViewHolder {
                val view = LayoutInflater.from(this@GoalMain)
                    .inflate(R.layout.acedimic_goal_list, parent, false)
                return GoalMainViewHolder(view, ::getItem, ::showEditGoalDialog) { position ->
                    snapshots.getSnapshot(position).id
                }
            }


            override fun onBindViewHolder(holder: GoalMainViewHolder, position: Int, model: Goal) {

                //   val tvMinutesFocus: TextView = holder.itemView.findViewById(R.id.item_priority_level)
                val tvGTitle: TextView = holder.itemView.findViewById(R.id.txtShowTitle)
                val tvEndTime: TextView = holder.itemView.findViewById(R.id.txtShowTime)
                // val tvStartDate: TextView = holder.itemView.findViewById(R.id.txtstartdate)
                val tvDueDate: TextView = holder.itemView.findViewById(R.id.txtenddate)
                val tvToDo: TextView = holder.itemView.findViewById(R.id.item_to_do)
                val progressBar = findViewById<ProgressBar>(R.id.AprogressBar)



                tvGTitle.text = model.title
                tvDueDate.text=model.duedate
                tvEndTime.text=model.startTime
                tvToDo.text=model.tasks
                tvGTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

           //progress bar

                val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
                val currentDate = Date()
                val dueDate = dateFormat.parse(model.duedate)
                val timeRemaining = dueDate.time - currentDate.time
                val daysRemaining = timeRemaining / (1000 * 60 * 60 * 24) // convert milliseconds to days
                val progress = when {
                    daysRemaining <= 0 -> 100 // if due date has passed, progress is 100%
                    daysRemaining >= 21 -> 0 // if due date is more than 3 weeks away, progress is 0%
                    else -> ((21 - daysRemaining).toDouble() / 21 * 100).toInt() // calculate progress as percentage of days remaining up to 3 weeks
                }


                if (progressBar != null) {
                    progressBar.progress = progress
                }



                val goal = getItem(position)
                val documentId = snapshots.getSnapshot(position).id


                holder.itemView.setOnClickListener {
                    showEditGoalDialog(goal, documentId)
                }

            }
        }
        GoalRecyclerView.adapter = adapter
        GoalRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    //notification

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Goal Notification Channel"
            val descriptionText = "Channel for goal reminders"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(GOAL_NOTIFICATION_CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                    enableLights(true)
                    lightColor = Color.RED
                    enableVibration(true)
                }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }

//    private fun scheduleGoalNotificationWorker() {
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresBatteryNotLow(true)
//            .build()
//        val goalNotificationWorker = PeriodicWorkRequestBuilder<GoalNotificationWorker>(1, TimeUnit.DAYS)
//            .setConstraints(constraints)
//            .build()
//        WorkManager.getInstance(applicationContext)
//            .enqueueUniquePeriodicWork(
//                GOAL_NOTIFICATION_WORKER_TAG,
//                ExistingPeriodicWorkPolicy.KEEP,
//                goalNotificationWorker
//            )
//
//        WorkManager.getInstance(applicationContext)
//            .enqueueUniquePeriodicWork(GOAL_NOTIFICATION_WORKER_TAG, ExistingPeriodicWorkPolicy.REPLACE, goalNotificationWorker)
//        // Set notification time to 12:00pm every day
//        val goalNotificationTime = Calendar.getInstance().apply {
//            set(Calendar.HOUR_OF_DAY, 12)
//            set(Calendar.MINUTE, 0)
//            set(Calendar.SECOND, 0)
//        }.timeInMillis
//
//        val currentTime = System.currentTimeMillis()
//        if (goalNotificationTime > currentTime) {
//            // Goal notification time is in the future, schedule the worker to run at this time
//            val delay = goalNotificationTime - currentTime
//            val notificationWorkRequest = OneTimeWorkRequestBuilder<GoalNotificationWorker>()
//                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
//                .setConstraints(constraints)
//                .build()
//
//            WorkManager.getInstance(applicationContext).enqueue(notificationWorkRequest)
//        }
//    }
//
//    class GoalNotificationWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
//
//        override fun doWork(): Result {
//            sendNotification()
//            return Result.success()
//        }
//
//        private fun sendNotification() {
//            val notificationBuilder = NotificationCompat.Builder(applicationContext, GOAL_NOTIFICATION_CHANNEL_ID)
//                .setSmallIcon(R.drawable.theme1)
//                .setContentTitle("It's time to work on your goals!")
//                .setContentText("You can do it!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setAutoCancel(true)
//
//            val notificationManager: NotificationManager =
//                applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//            notificationManager.notify(GOAL_NOTIFICATION_ID, notificationBuilder.build())
//        }
//    }

// schedule and send notification
    private fun scheduleGoalNotificationWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val goalNotificationWorker = PeriodicWorkRequestBuilder<GoalNotificationWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                GOAL_NOTIFICATION_WORKER_TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                goalNotificationWorker
            )

        // Set notification time to 12:00 every day
        val goalNotificationTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.timeInMillis

        val currentTime = System.currentTimeMillis()
        if (goalNotificationTime > currentTime) {
            // Goal notification time is in the future, schedule the worker to run at this time
            val delay = goalNotificationTime - currentTime
            val notificationWorkRequest = OneTimeWorkRequestBuilder<GoalNotificationWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(applicationContext).enqueue(notificationWorkRequest)
        }
    }

    class GoalNotificationWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

        override fun doWork(): Result {
            sendNotification()
            return Result.success()
        }

        private fun sendNotification() {
            val notificationBuilder = NotificationCompat.Builder(applicationContext, GOAL_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.theme1)
                .setContentTitle("It's time to work on your goals!")
                .setContentText("Time flies, but your knowledge remains. Study persistently!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.notify(GOAL_NOTIFICATION_ID, notificationBuilder.build())
        }
    }


//new goal
    fun openNewGoal(view: View){
        startActivity(Intent(this, AddGoal::class.java))
        finish()
    }
//reminder
    fun remind_me(view: View) {
        val calendarEvent: Calendar = Calendar.getInstance()
        val intent = Intent(Intent.ACTION_EDIT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra("beginTime", calendarEvent.timeInMillis)
        intent.putExtra("allDay", true)


        intent.putExtra("allDay", true)
        intent.putExtra("rule", "FREQ=YEARLY")
        intent.putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
        intent.putExtra("title", "Calendar Event")
        startActivity(intent)
    }

    //goal edit

    private fun showEditGoalDialog(goal: Goal, documentId: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_edit_goal, null)
        dialogBuilder.setView(dialogView)

        val input = dialogView.findViewById<EditText>(R.id.editTextTasks)
        input.setText(goal.tasks)

        dialogBuilder.setTitle("Edit Tasks")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            val newTask = input.text.toString()
            if (newTask.isNotBlank()) {
                val updateTask = goal.copy(tasks = newTask)
                updateGoalInFirestore(updateTask, documentId)
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }


    private fun updateGoalInFirestore(task: Goal, documentId: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val habitRef = db.collection("goal").document(documentId)
            habitRef.set(task)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Tasks updated successfully")
                }
                .addOnFailureListener {
                    Log.e(ContentValues.TAG, "Error updating habit", it)
                }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }
}






