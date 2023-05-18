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
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serene.*
import com.example.serene.expert_support.Channeling
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_daily_habit_list.*
import kotlinx.android.synthetic.main.activity_journal_main.*
import kotlinx.android.synthetic.main.daily_habit_card.*
import java.util.*


data class DailyHabit(


        val title: String="",

        val startTime:String="",
    val priorityLevel:String=""


        )

class  DailyHabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemPriImg: ImageView = itemView.findViewById(R.id.item_priority_level)
}
class DailyHabitsMain : AppCompatActivity() {

    private val q_habits = listOf(
        "Read a book",
        "Take a 5-minute walk outside",
        "Drink a glass of water",
    "Take a 5-minute walk outside.",
        "Practice deep breathing for 1 minute",
        "Stretch your arms and legs for a quick break",
    "Do 10 push-ups or modified push-ups",
        "Write down three things you're grateful for",
        "Listen to your favorite energizing song and dance for a minute"
    )

        val db = Firebase.firestore

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_daily_habit_list)

            val habitTextView = findViewById<TextView>(R.id.habit_text_view)
//            val habitSelectorButton = findViewById<Button>(R.id.habit_selector_button)

            val random = Random()

//            habitSelectorButton.setOnClickListener {
//                val habitSelectorDialog = HabitSelectorDialog(this, habits) { selectedHabit ->
//                    habitTextView.text = selectedHabit
//                }
//                habitSelectorDialog.show()
//            }

            findViewById<Button>(R.id.random_habit_button).setOnClickListener {
                val randomHabit = q_habits[random.nextInt(q_habits.size)]
                habitTextView.text = randomHabit
            }
//
            supportActionBar?.title = getString(R.string.h_s)
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




                override fun onBindViewHolder(holder: DailyHabitViewHolder, position: Int, model: DailyHabit) {
                    val tvPL: TextView = holder.itemView.findViewById(R.id.pri)
                    val tvTitle: TextView = holder.itemView.findViewById(R.id.item_tv_title)
                    val tvStartTime: TextView = holder.itemView.findViewById(R.id.item_tv_start_time)
                    //val tvDate: TextView = holder.itemView.findViewById(android.R.id.text2)
                    //val tvTitle: TextView = holder.itemView.findViewById(android.R.id.text2)
                    // tvDate.text = model.datetime.toString()
                    tvPL.text = model.priorityLevel
                    tvTitle.text = model.title
                    tvStartTime.text = model.startTime

//                    tvDate.setTextColor(Color.parseColor("#00FF00"))
//                    tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)

                    tvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                //    tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)


                    if(model.priorityLevel == "High"){
                        tvPL.setTextColor(Color.parseColor("#ff1744"))
                    }
                    else if(model.priorityLevel == "Medium"){
                        tvPL.setTextColor(Color.parseColor("#00e676"))
                    }
                    else{
                        tvPL.setTextColor(Color.parseColor("#ff9800"))
                    }

//                    else {
//                        item_tv_title.setTextColor(Color.parseColor("#ff0000"))
//                    }

                    val item_priority_level = holder.itemPriImg
                    if (model.priorityLevel == "High") {
                        item_priority_level.setImageResource(R.drawable.selected_date_background)
                    } else if (model.priorityLevel == "Medium") {
                        item_priority_level.setImageResource(R.drawable.ic_priority_low)
                    }

                    else {
                        item_priority_level.setImageResource(R.drawable.ic_priority_medium)
                    }


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



    fun addNewHabit(view: View){
            startActivity(Intent(this,AddDailyHabits::class.java))
            finish()
        }
    fun h1(view: View){
        startActivity(Intent(this, SocialHabit::class.java))
        finish()
    }
//    fun h2(view: View){
//        startActivity(Intent(this, RandomHabitTwo::class.java))
//        finish()
//    }
fun h2(view: View){
    startActivity(Intent(this, SocialHabitMain::class.java))
    finish()
}
    fun h3(view: View){
        startActivity(Intent(this, FutureYou::class.java))
        finish()
    }
    fun h4(view: View){
        startActivity(Intent(this, RandomHabitFour::class.java))
        finish()
    }
    fun h5(view: View){
        startActivity(Intent(this, RandomHabitFive::class.java))
        finish()
    }
    fun h6(view: View){
        startActivity(Intent(this, RandomHabitSix::class.java))
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }
    }


