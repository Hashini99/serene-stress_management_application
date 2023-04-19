package com.example.serene.academic_goal

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serene.MainTasks
import com.example.serene.R
import com.example.serene.daily_habits.AddDailyHabits
import com.example.serene.daily_habits.DailyHabit
import com.example.serene.daily_habits.DailyHabitSingleView
import com.example.serene.daily_habits.DailyHabitViewHolder
import com.example.serene.expert_support.ChannelList
import com.example.serene.expert_support.Expert
import com.example.serene.expert_support.ExpertSingleView
import com.example.serene.expert_support.ExpertViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_acdemic_goal_main.*
import kotlinx.android.synthetic.main.activity_daily_habit_list.*
import kotlinx.android.synthetic.main.activity_expert_list_view.*
import java.util.*

data class Goal(
    // val datetime:Date,
    val title: String = "",
  //  val tasks:String="",
  val duedate:String="",
val date:String="",
       val startTime:String=""
)

class GoalMainViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

class GoalMain : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acdemic_goal_main)
        try {
            this.supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(
                    getResources()
                        .getColor(R.color.dark_blue_grey)
                )
            )
        } catch (e: NullPointerException) {
        }
        val query = db.collection("goals")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
        //.orderBy("datetime", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Goal>().setQuery(query, Goal::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<Goal, GoalMainViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalMainViewHolder {
                val view = LayoutInflater.from(this@GoalMain)
                    .inflate(R.layout.acedimic_goal_list, parent, false)
                return GoalMainViewHolder(view)
            }
//
//            override fun onBindViewHolder(holder: JournalViewHolder, position: Int, model: Note) {
//                val tvStatus: TextView = holder.itemView.findViewById(android.R.id.text1)
//                val tvDate: TextView = holder.itemView.findViewById(android.R.id.text2)
//                tvStatus.text = model.dateTime
//                tvDate.text = model.title
//                tvDate.text = model.description
//
//                tvDate.setTextColor(Color.parseColor("#000000"))
//                tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
//
//                tvStatus.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
//                tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)


            override fun onBindViewHolder(holder: GoalMainViewHolder, position: Int, model: Goal) {
                //   val tvMinutesFocus: TextView = holder.itemView.findViewById(R.id.item_priority_level)
                val tvGTitle: TextView = holder.itemView.findViewById(R.id.txtShowTitle)
                val tvEndTime: TextView = holder.itemView.findViewById(R.id.txtShowTime)
               // val tvStartDate: TextView = holder.itemView.findViewById(R.id.txtstartdate)
                val tvDueDate: TextView = holder.itemView.findViewById(R.id.txtenddate)


                tvGTitle.text = model.title
               tvDueDate.text=model.duedate
                tvEndTime.text=model.startTime

                tvGTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                //    tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)



                val documentId = snapshots.getSnapshot(position).id

                holder.itemView.setOnClickListener {
                    changePageToGoalSingleView(documentId)
                }
            }
        }
        goal_recycle_view.adapter = adapter
        goal_recycle_view.layoutManager = LinearLayoutManager(this)
    }
    fun changePageToGoalSingleView(docID: String){
        val intent = Intent(this, AcademicGoalSingleView::class.java)
        intent.putExtra("docID", docID)
        startActivity(intent)
    }
    fun openNewGoal(view: View){
        startActivity(Intent(this, AddGoal::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }
}






