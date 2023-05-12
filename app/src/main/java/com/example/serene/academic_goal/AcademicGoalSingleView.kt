package com.example.serene.academic_goal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.JournalMainActivity
import com.example.serene.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_acdemic_goal_single_view.*
import kotlinx.android.synthetic.main.activity_journal_single_view.*
import java.util.*

class AcademicGoalSingleView : AppCompatActivity() {

    lateinit var documentID: String
    lateinit var tasks: String
    lateinit var title: String
    lateinit var duedate: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//        }

        setContentView(R.layout.activity_acdemic_goal_single_view)

        documentID = intent.getStringExtra("docID").toString()

        FirebaseFirestore.getInstance().collection("goal").document(documentID.toString()).get()
            .addOnSuccessListener {


                title = it.data!!.getValue("title").toString()
                tasks = it.data!!.getValue("tasks").toString()
                duedate = it.data!!.getValue("duedate").toString()


                txt_a_date.text = it.data!!.getValue("duedate").toString()
//                txt_a_time.text = it.data!!.getValue("time").toString()
                txt_a_title.text = it.data!!.getValue("title").toString()
                txt_steps.text = it.data!!.getValue("tasks").toString()

                //descriptionVisibility()

            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Cannot Get Data from Server", Toast.LENGTH_LONG)
                    .show()
            }

    }



    fun delete_goal(view: View) {
        FirebaseFirestore.getInstance().collection("goal").document(documentID).delete()
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Goal Deleted Successful", Toast.LENGTH_LONG)
                    .show()
                startActivity(Intent(this, GoalMain::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Cannot Delete the Goal", Toast.LENGTH_LONG)
                    .show()
            }
    }
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

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, GoalMain::class.java))
        finish()
    }
}