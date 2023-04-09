package com.example.serene.daily_habits

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serene.JournalAddEditNoteActivity
import com.example.serene.JournalSingleView
import com.example.serene.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_daily_habit_list.*
import kotlinx.android.synthetic.main.activity_journal_main.*


    data class DailyHabit(
      //  // val datetime:Date,
        // //val date: String = "",

        val title: String="",
      // val minutesFocus:Int,
        val startTime:String="",
       // val minutesFocus:String,

        )

class  DailyHabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class DailyHabitsMain : AppCompatActivity() {
        val db = Firebase.firestore

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_daily_habit_list)
            try {
                this.supportActionBar!!.setBackgroundDrawable(
                    ColorDrawable(
                        getResources()
                            .getColor(R.color.dark_blue_grey)
                    )
                )
            } catch (e: NullPointerException) {
            }
            val query = db.collection("habits")
                .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
                //.orderBy("datetime", Query.Direction.DESCENDING)
            val options = FirestoreRecyclerOptions.Builder<DailyHabit>().setQuery(query, DailyHabit::class.java)
                .setLifecycleOwner(this).build()
            val adapter = object : FirestoreRecyclerAdapter<DailyHabit, DailyHabitViewHolder>(options) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyHabitViewHolder {
                    val view = LayoutInflater.from(this@DailyHabitsMain)
                        .inflate(R.layout.daily_habit_card, parent, false)
                    return DailyHabitViewHolder(view)
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


                override fun onBindViewHolder(holder: DailyHabitViewHolder, position: Int, model: DailyHabit) {
                 //   val tvMinutesFocus: TextView = holder.itemView.findViewById(R.id.item_priority_level)
                    val tvTitle: TextView = holder.itemView.findViewById(R.id.item_tv_title)
                    val tvStartTime: TextView = holder.itemView.findViewById(R.id.item_tv_start_time)
                    //val tvDate: TextView = holder.itemView.findViewById(android.R.id.text2)
                    //val tvTitle: TextView = holder.itemView.findViewById(android.R.id.text2)
                    // tvDate.text = model.datetime.toString()
                  //  tvMinutesFocus.text = model.minutesFocus.toString()
                    tvTitle.text = model.title
                    tvStartTime.text = model.startTime

//                    tvDate.setTextColor(Color.parseColor("#00FF00"))
//                    tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)

                    tvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                //    tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)







                    val documentId = snapshots.getSnapshot(position).id

                    holder.itemView.setOnClickListener {
                       changePageToHabitSingleView(documentId)
                    }
                }
            }
            dailyhabitsRecyclerView.adapter = adapter
            dailyhabitsRecyclerView.layoutManager = LinearLayoutManager(this)
        }
        fun changePageToHabitSingleView(docID: String){
            val intent = Intent(this, DailyHabitSingleView::class.java)
            intent.putExtra("docID", docID)
            startActivity(intent)
        }
        fun addNewJournal(view: View){
            startActivity(Intent(this,AddDailyHabits::class.java))
            finish()
        }
    }


