//package com.example.serene.daily_habits
//
//import android.os.Bundle
//import android.os.Parcelable
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.serene.R
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.firestore.ktx.toObject
//import com.google.firebase.firestore.ktx.toObjects
//import com.google.firebase.ktx.Firebase
//import kotlinx.android.parcel.Parcelize
//
//@Parcelize
//data class SocialHabitDocument(
//    val user: String = "",
//    val title: String = "",
//    val difficulty: Int = 0,
//    val progress: Int = 0
//): Parcelable
//
//
//class SocialHabitMain  : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: HabitListAdapter
//    private lateinit var firestore: FirebaseFirestore
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.socil_habit_re_view)
//
//        firestore = Firebase.firestore
//
//        recyclerView = findViewById(R.id.habits_history_list)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        if (userId != null) {
//            firestore.collection("futurehabits")
//                .whereEqualTo("user", userId)
//                .get()
//                .addOnSuccessListener { documents ->
//                    val habits = documents.toObjects<SocialHabitDocument>()
//                    adapter = HabitListAdapter(habits)
//                    recyclerView.adapter = adapter
//                }
//        }
//    }
//
//    private inner class HabitListAdapter(private val habits: List<SocialHabitDocument>) :
//        RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
//
//        inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val title: TextView = itemView.findViewById(R.id.habit_title)
//            val difficulty: TextView = itemView.findViewById(R.id.habit_difficulty)
//            val progress: TextView = itemView.findViewById(R.id.habit_progress)
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
//            val itemView =
//                LayoutInflater.from(parent.context)
//                    .inflate(R.layout.social_habit_history, parent, false)
//            return HabitViewHolder(itemView)
//        }
//
//        override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
//            val habit = habits[position]
//            holder.title.text = habit.title
//            holder.difficulty.text = "Difficulty: ${habit.difficulty}"
//            holder.progress.text = "Progress: ${habits[position].progress}%"
//        }
//
//        override fun getItemCount() = habits.size
//    }
//}

package com.example.serene.daily_habits

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serene.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SocialHabitDocument(
    val user: String = "",
    val title: String = "",
    val difficulty: Int = 0,
    val progress: Int = 0
) : Parcelable

class SocialHabitMain : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HabitListAdapter
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.socil_habit_re_view)

        firestore = Firebase.firestore

        recyclerView = findViewById(R.id.habits_history_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            firestore.collection("futurehabits")
                // .whereEqualTo("user", userId)
                .get()
                .addOnSuccessListener { documents ->
                    val habits = documents.toObjects<SocialHabitDocument>()
                    adapter = HabitListAdapter(habits)
                    recyclerView.adapter = adapter
                }
        }
    }

    private inner class HabitListAdapter(private val habits: List<SocialHabitDocument>) :
        RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

        inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.habit_title)
            val difficulty: TextView = itemView.findViewById(R.id.habit_difficulty)
            val progress: TextView = itemView.findViewById(R.id.habit_progress)

            init {
                itemView.setOnClickListener {
                    val habit = habits[adapterPosition]
                    showEditProgressDialog(habit)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.social_habit_history, parent, false)
            return HabitViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
            val habit = habits[position]
            holder.title.text = habit.title
            holder.difficulty.text = "Difficulty: ${habit.difficulty}"
            holder.progress.text = "Progress: ${habit.progress}%"
        }

        override fun getItemCount() = habits.size
    }

    private fun showEditProgressDialog(habit: SocialHabitDocument) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Progress")

        val input = EditText(this)
        input.setText(habit.progress.toString())
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->
            val newProgress = input.text.toString().toIntOrNull()
            if (newProgress != null) {
                val updatedHabit = habit.copy(progress = newProgress)
                updateHabitInFirestore(updatedHabit)
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun updateHabitInFirestore(habit: SocialHabitDocument) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val habitRef = firestore.collection("futurehabits").document(userId)
            habitRef.set(habit)
                .addOnSuccessListener {
                    Log.d(TAG, "Habit updated successfully")
                }
                .addOnFailureListener {
                    Log.e(TAG, "Error updating habit", it)
                }
        }
    }
    fun addNewSHabit(view: View){
        startActivity(Intent(this,FutureYou::class.java))
        finish()
    }
}