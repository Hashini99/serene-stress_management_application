package com.example.serene.history

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.DropBoxManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serene.MainTasks
import com.example.serene.R
import com.example.serene.daily_habits.DailyHabit
import com.example.serene.daily_habits.DailyHabitViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_daily_habit_list.*
import kotlinx.android.synthetic.main.activity_stress_tracker_overview.*
import kotlinx.android.synthetic.main.stress_card.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
//
//class StressTrackerOverview : AppCompatActivity() {
//
//    // declare UI elements
//    lateinit var lineChart: LineChart
//    lateinit var newMeasurePSS: Button
//    lateinit var newMeasureHRV: Button
//    lateinit var learnMore: Button
//    lateinit var documentID: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_stress_tracker_overview)
//
//        // initialize UI elements
//        lineChart = findViewById(R.id.lineChart)
//        documentID = intent.getStringExtra("docID").toString()
//        val db = Firebase.firestore
//        val data = mutableListOf<Entry>()
//
//        db.collection("quiz").get().addOnSuccessListener { documents ->
//            for (doc in documents) {
//                val value = doc.getDouble("score")?.toFloat() ?: 0f
//                val timestamp = doc.getTimestamp("datetime")?.toDate()?.time?.toFloat() ?: 0f
//                data.add(Entry(timestamp, value))
////                val date = doc.getTimestamp("datetime")?.toDate()?.time?.toFloat() ?: 0f
////                data.add(Entry(date, value))
//            }
//
//            // Create the line chart
//            //val lineChart = LineChart(context)
//            val dataSet = LineDataSet(data, "My Data")
//            lineChart.data = LineData(dataSet)
//            lineChart.invalidate()
//
//            dataSet.color = Color.BLUE
//            dataSet.valueTextColor = Color.BLACK
//            dataSet.valueTextSize = 12f
//
//            lineChart.description.isEnabled = false
//            lineChart.legend.isEnabled = false
//            lineChart.setTouchEnabled(true)
//
//            val xAxis = lineChart.xAxis
//            xAxis.position = XAxis.XAxisPosition.BOTTOM
//            xAxis.labelRotationAngle = 45f
//
//            val yAxis = lineChart.axisLeft
//            yAxis.axisMinimum = 0f
//
//            lineChart.axisRight.isEnabled = false
//        }
//
//    }
//}


//data class StresScore(
//
//
//    val score: Double,
//
//    val date:String="",
//  //  val priorityLevel:String=""
//
//
//)
class StresScore {
    var score: Double = 0.0
    //var timestamp: Long = 0
    val date:String=""
    constructor() // Add a no-argument constructor
}

class  StressScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class StressTrackerOverview : AppCompatActivity() {
    val db = Firebase.firestore
    // declare UI elements
    lateinit var lineChart: LineChart
    lateinit var newMeasurePSS: Button
    lateinit var newMeasureHRV: Button
    lateinit var learnMore: Button
    lateinit var documentID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stress_tracker_overview)
        try {
            this.supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(
                    getResources()
                        .getColor(R.color.dark_blue_grey)
                )
            )
        } catch (e: NullPointerException) {
        }
        //// initialize UI elements
        lineChart = findViewById(R.id.lineChart)
        documentID = intent.getStringExtra("docID").toString()
        val db = Firebase.firestore
        val data = mutableListOf<Entry>()

        FirebaseFirestore.getInstance().collection("quiz").document(documentID.toString()).get()
            .addOnSuccessListener {


//                a_title = it.data!!.getValue("title").toString()
//                tasks = it.data!!.getValue("tasks").toString()





        db.collection("quiz").get().addOnSuccessListener { documents ->
            for (doc in documents) {
                val value = doc.getDouble("score")?.toFloat() ?: 0f
                val timestamp = doc.getTimestamp("datetime")?.toDate()?.time?.toFloat() ?: 0f
                data.add(Entry(timestamp, value))
//                val date = doc.getTimestamp("datetime")?.toDate()?.time?.toFloat() ?: 0f
//                data.add(Entry(date, value))


                // Create the line chart

                //  val lineChart = LineChart(Context)
                // val lineChart = LineChart(Context)
                val dataSet = LineDataSet(data, "My Data")
                lineChart.data = LineData(dataSet)
                lineChart.invalidate()

                dataSet.color = Color.RED
                dataSet.valueTextColor = Color.BLACK
                dataSet.valueTextSize = 12f

                lineChart.description.isEnabled = false
                lineChart.legend.isEnabled = false
                lineChart.setTouchEnabled(true)

                val xAxis = lineChart.xAxis
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.labelRotationAngle = 45f

                val yAxis = lineChart.axisLeft
                yAxis.axisMinimum = 0f

                lineChart.axisRight.isEnabled = false

                //layout.addView(lineChart)

                // layout.addView(lineChart)
            }

        }


        val query = db.collection("quiz")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
        //.orderBy("datetime", Query.Direction.DESCENDING)
        val options =
            FirestoreRecyclerOptions.Builder<StresScore>().setQuery(query, StresScore::class.java)
                .setLifecycleOwner(this).build()
        val adapter =
            object : FirestoreRecyclerAdapter<StresScore, StressScoreViewHolder>(options) {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): StressScoreViewHolder {
                    val view = LayoutInflater.from(this@StressTrackerOverview)
                        .inflate(R.layout.stress_card, parent, false)
                    return StressScoreViewHolder(view)
                }


                override fun onBindViewHolder(
                    holder: StressScoreViewHolder,
                    position: Int,
                    model: StresScore
                ) {
                    val tvSc: TextView = holder.itemView.findViewById(R.id.item_tv_score)

                    val tvST: TextView = holder.itemView.findViewById(R.id. item_tv_start_time_date)

                    tvSc.text= model.score.toString()

                    tvST.text = model.date

//                    tvDate.setTextColor(Color.parseColor("#00FF00"))
//                    tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)

                    //tvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    //    tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)

//
//                if(model.priorityLevel == "High"){
//                    tvPL.setTextColor(Color.parseColor("#dbba00"))
//                }
//                else if(model.priorityLevel == "Medium"){
//                    tvPL.setTextColor(Color.parseColor("#ffaa00"))
//                }
//                else{
//                    tvPL.setTextColor(Color.parseColor("#0091ff"))
//                }

//                    else {
//                        item_tv_title.setTextColor(Color.parseColor("#ff0000"))
//                    }


                    val documentId = snapshots.getSnapshot(position).id

                    holder.itemView.setOnClickListener {
                        //changePageToHabitSingleView(documentId)
                    }
                }
            }
        ScoreRecyclerView.adapter = adapter
        ScoreRecyclerView.layoutManager = LinearLayoutManager(this)
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, HistoryMain::class.java))
//        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HistoryMain::class.java))
        finish()
    }
}

