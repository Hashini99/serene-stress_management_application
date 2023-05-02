package com.example.serene.history

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
//import com.example.serene.databinding.TimelineDayFragmentBinding
import com.example.serene.expert_support.TimelineDayFragment
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_daily_habit_list.*
import kotlinx.android.synthetic.main.activity_mood_history.*
//import kotlinx.android.synthetic.main.activity_update_profile.view.*
import kotlinx.android.synthetic.main.mood_history_card.*
import java.util.*



data class Moods(


    val selected_mood: String="",
    //val mood:Int,
    val date:String="",
    val time:String="",
    //val img:Int


)

class  MoodHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class MoodHistory : AppCompatActivity() {
    val db = Firebase.firestore

    companion object {
        private const val BUNDLE_POSITION_KEY = "POSITION"
        fun instantiate(pos: Int) = TimelineDayFragment().apply {
            arguments = Bundle().apply { putInt(BUNDLE_POSITION_KEY, pos) }
        }
    }

//    private lateinit var binding: TimelineDayFragmentBinding
//    private var position = 0



    override fun onCreate(savedInstanceState: Bundle?) {






        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        supportActionBar?.title = getString(R.string.m_j)

        val query = db.collection("mood")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
        .orderBy("date", Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<Moods>().setQuery(query, Moods::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<Moods, MoodHistoryViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,): MoodHistoryViewHolder {
                val view = LayoutInflater.from(this@MoodHistory)
                    .inflate(R.layout.mood_history_card, parent, false)
                return MoodHistoryViewHolder(view)
            }


            override fun onBindViewHolder(holder: MoodHistoryViewHolder, position: Int, model: Moods) {

                val tv_mood: TextView = holder.itemView.findViewById(R.id.item_mood)
                val tvSTime: TextView = holder.itemView.findViewById(R.id.item_mood_time)
                val tvSDate: TextView = holder.itemView.findViewById(R.id.item_mood_date)
               // val tv_mood_i: ImageView = holder.itemView.findViewById(R.id.item_mood_img)

                tv_mood.text = model.selected_mood
                tvSTime.text = model.time
                tvSDate.text = model.date
              // tv_mood_i.imageAlpha = model.img


//change colour
                if(model.selected_mood == "happy"){
                    tv_mood.setTextColor(Color.parseColor("#00e676"))
                }
                else if(model.selected_mood == "sad"){
                    tv_mood.setTextColor(Color.parseColor("#f57c00"))
                }
                else if(model.selected_mood == "neutral"){
                   tv_mood.setTextColor(Color.parseColor("#0091ff"))
               }
                else if(model.selected_mood == "angry"){
                    tv_mood.setTextColor(Color.parseColor("#ff1744"))
                }
                else {
                    tv_mood.setTextColor(Color.parseColor("#3C1F7B"))
                }

// change image
//                if(model.mood == "sad"){
//                    item_mood_img.setImageResource(R.drawable.happy);
//                }
//                else if(model.mood == "happy"){
//                    item_mood_img.setImageResource(R.drawable.headphones);
//                }
//                else if(model.mood == "moderate"){
//                    item_mood_img.setImageResource(R.drawable.home);
//                }
//                else if(model.mood == "angry"){
//                    item_mood_img.setImageResource(R.drawable.mic);
//                }
//                else {
//                    item_mood_img.setImageResource(R.drawable.nature);
//                }





                val documentId = snapshots.getSnapshot(position).id

                holder.itemView.setOnClickListener {
                  //  changePageToHabitSingleView(documentId)
                }
            }
        }
        moodRecyclerView.adapter = adapter
        moodRecyclerView.layoutManager = LinearLayoutManager(this)
    }






    fun addNewJournal(view: View){
        startActivity(Intent(this, AddDailyHabits::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HistoryMain::class.java))
        finish()
    }
}



//data class Moods(
//    val selected_mood: String = "",
//    val date: String = "",
//    val time: String = "",
//    val img: Int // add a property for image ID
//)
//
//class MoodHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//
//class MoodHistory : AppCompatActivity() {
//    private val db = Firebase.firestore
//
//    companion object {
//        private const val BUNDLE_POSITION_KEY = "POSITION"
//        fun instantiate(pos: Int) = TimelineDayFragment().apply {
//            arguments = Bundle().apply { putInt(BUNDLE_POSITION_KEY, pos) }
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_mood_history)
//
//        supportActionBar?.title = getString(R.string.m_j)
//
//        val query = db.collection("mood")
//            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
//            .orderBy("date", Query.Direction.DESCENDING)
//
//        val options = FirestoreRecyclerOptions.Builder<Moods>()
//            .setQuery(query, Moods::class.java)
//            .setLifecycleOwner(this)
//            .build()
//
//        val adapter = object : FirestoreRecyclerAdapter<Moods, MoodHistoryViewHolder>(options) {
//            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodHistoryViewHolder {
//                val view = LayoutInflater.from(this@MoodHistory)
//                    .inflate(R.layout.mood_history_card, parent, false)
//                return MoodHistoryViewHolder(view)
//            }
//
//            override fun onBindViewHolder(holder: MoodHistoryViewHolder, position: Int, model: Moods) {
//                val tv_mood: TextView = holder.itemView.findViewById(R.id.item_mood)
//                val tvSTime: TextView = holder.itemView.findViewById(R.id.item_mood_time)
//                val tvSDate: TextView = holder.itemView.findViewById(R.id.item_mood_date)
//                val iv_mood: ImageView = holder.itemView.findViewById(R.id.item_mood_img)
//
//                tv_mood.text = model.selected_mood
//                tvSTime.text = model.time
//                tvSDate.text = model.date
//                iv_mood.setImageResource(getMoodImage(model.selected_mood)) // set the image resource based on the mood
//
//                // change color
//                tv_mood.setTextColor(getMoodColor(model.selected_mood))
//
//                val documentId = snapshots.getSnapshot(position).id
//                holder.itemView.setOnClickListener {
//                    // changePageToHabitSingleView(documentId)
//                }
//            }
//        }
//
//        moodRecyclerView.adapter = adapter
//        moodRecyclerView.layoutManager = LinearLayoutManager(this)
//    }
//
//    private fun getMoodColor(mood: String): Int {
//        return when (mood) {
//            "happy" -> Color.parseColor("#dbba00")
//            "sad" -> Color.parseColor("#ffaa00")
//            "moderate" -> Color.parseColor("#0091ff")
//            "angry" -> Color.parseColor("#00ff00")
//            else -> Color.parseColor("#ff0000")
//        }
//    }
//
//    private fun getMoodImage(mood: String): Int {
//        return when (mood) {
//            "happy" -> R.drawable.happy
//            "sad" -> R.drawable.sad
//            "moderate" -> R.drawable.happy
//            "angry" -> R.drawable.nature
//            else -> R.drawable.nature
//        }
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, HistoryMain::class.java))
//        finish()
//    }
//}
