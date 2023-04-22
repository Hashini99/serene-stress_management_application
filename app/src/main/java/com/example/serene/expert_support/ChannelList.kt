package com.example.serene.expert_support

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
import com.example.serene.R
import com.example.serene.daily_habits.AddDailyHabits
import com.example.serene.daily_habits.DailyHabit
import com.example.serene.daily_habits.DailyHabitSingleView
import com.example.serene.daily_habits.DailyHabitViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_channel_list.*
import kotlinx.android.synthetic.main.activity_daily_habit_list.*
import java.util.*


data class ExpertChannel(
    //  // val datetime:Date,
    // //val date: String = "",

    val counsellor: String="",
    // val minutesFocus:Int,
    val date:String="",
    // val minutesFocus:String,

)
class ChannelListViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView)

class ChannelList : AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel_list)
        supportActionBar?.title = getString(R.string.a_h)
        val query = db.collection("bookings")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
        //.orderBy("datetime", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<ExpertChannel>().setQuery(query, ExpertChannel::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<ExpertChannel,ChannelListViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelListViewHolder {
                val view = LayoutInflater.from(this@ChannelList)
                    .inflate(R.layout.channel_list_card, parent, false)
                return ChannelListViewHolder(view)
            }


            override fun onBindViewHolder(holder: ChannelListViewHolder, position: Int, model: ExpertChannel) {
                //   val tvMinutesFocus: TextView = holder.itemView.findViewById(R.id.item_priority_level)
                val tvDname: TextView = holder.itemView.findViewById(R.id.item_tv_Dname)
                val tvStartD: TextView = holder.itemView.findViewById(R.id.item_tv_start_date)
                //val tvDate: TextView = holder.itemView.findViewById(android.R.id.text2)
                //val tvTitle: TextView = holder.itemView.findViewById(android.R.id.text2)
                // tvDate.text = model.datetime.toString()
                //  tvMinutesFocus.text = model.minutesFocus.toString()
                tvDname.text = model.counsellor
                tvStartD.text = model.date

//                    tvDate.setTextColor(Color.parseColor("#00FF00"))
//                    tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)

                tvDname.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                //    tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)







                val documentId = snapshots.getSnapshot(position).id

                holder.itemView.setOnClickListener {
                    changePageToBookingSingleView(documentId)
                }
            }
        }
        channellistRecyclerView.adapter = adapter
        channellistRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun changePageToBookingSingleView(docID: String){
        val intent = Intent(this, ChannelSingleView::class.java)
        intent.putExtra("docID", docID)
        startActivity(intent)
    }

    fun reminder(view: View) {
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



    fun addNewChannel(view: View){
        startActivity(Intent(this, Channeling::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ExpertSupportMain::class.java))
        finish()
    }
}


