

//package com.example.serene.daily_habits
//
//import android.app.AlertDialog
//import android.content.ContentValues.TAG
//import android.content.Intent
//import android.os.Bundle
//import android.os.Parcelable
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
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
//) : Parcelable {
//    var id: String = "" // Declare the id property in SocialHabitDocument
//}
//
//class SocialHabitMain : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: HabitListAdapter
//    private lateinit var firestore: FirebaseFirestore
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.socil_habit_re_view)
//        supportActionBar?.title = getString(R.string.y_h)
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
//                    habits.forEachIndexed { index, habit ->
//                        val habitId = documents.documents[index].id // Get the document ID
//                        habit.id = habitId // Assign the habitId to the habit's id property
//                    }
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
//
//            init {
//                itemView.setOnClickListener {
//                    val habit = habits[adapterPosition]
//                    showEditProgressDialog(habit)
//                }
//                itemView.setOnLongClickListener {
//                    val habit = habits[adapterPosition]
//                    showDeleteConfirmationDialog(habit)
//                    true
//                }
//            }
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
//            holder.progress.text = "Progress: ${habit.progress}%"
//        }
//
//        override fun getItemCount() = habits.size
//    }
//
//    private fun showEditProgressDialog(habit: SocialHabitDocument) {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Edit Progress")
//
//        val input = EditText(this)
//        input.setText(habit.progress.toString())
//        builder.setView(input)
//
//        builder.setPositiveButton("OK") { _, _ ->
//            val newProgress = input.text.toString().toIntOrNull()
//            if (newProgress != null) {
//                val updatedHabit = habit.copy(progress = newProgress)
//                updateHabitInFirestore(updatedHabit)
//            }
//        }
//        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
//
//        builder.show()
//    }
//
//    private fun updateHabitInFirestore(habit: SocialHabitDocument) {
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        if (userId != null) {
//            val habitRef = firestore.collection("futurehabits").document(userId) // Use userId as the document reference
//            habitRef.set(habit)
//                .addOnSuccessListener {
//                    Log.d(TAG, "Habit updated successfully")
//                }
//                .addOnFailureListener {
//                    Log.e(TAG, "Error updating habit", it)
//                }
//        }
//    }
//
//    fun addNewSHabit(view: View) {
//        startActivity(Intent(this, FutureYou::class.java))
//        finish()
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, HabitDevide::class.java))
//        finish()
//    }
//
//    private fun showDeleteConfirmationDialog(habit: SocialHabitDocument) {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Delete Habit")
//        builder.setMessage("Are you sure you want to delete this habit?")
//
//        builder.setPositiveButton("Delete") { _, _ ->
//            deleteHabitFromFirestore(habit)
//        }
//        builder.setNegativeButton("Cancel") { dialog, _ ->
//            dialog.cancel()
//        }
//
//        builder.show()
//    }
//
//    private fun deleteHabitFromFirestore(habit: SocialHabitDocument) {
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        if (userId != null) {
//            val habitRef = firestore.collection("futurehabits").document(habit.id)
//            habitRef.delete()
//                .addOnSuccessListener {
//                    Log.d(TAG, "Habit deleted successfully")
//                }
//                .addOnFailureListener {
//                    Log.e(TAG, "Error deleting habit", it)
//                }
//        }
//
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
import android.widget.Toast
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
) : Parcelable {
    var id: String = "" // Declare the id property in SocialHabitDocument
}

class SocialHabitMain : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HabitListAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var habits: MutableList<SocialHabitDocument>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.socil_habit_re_view)
        supportActionBar?.title = getString(R.string.y_h)
        firestore = Firebase.firestore
        habits = mutableListOf()
        recyclerView = findViewById(R.id.habits_history_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            firestore.collection("futurehabits")
                .whereEqualTo("user", userId)
                .get()
//                .addOnSuccessListener { documents ->
//                    habits = documents.toObjects<SocialHabitDocument>().toMutableList()
//                    documents.documents.forEachIndexed { index, document ->
//                        val habitId = document.id // Get the document ID
//                        habits[index].id = habitId // Assign the habitId to the habit's id property
//                    }
//                    adapter = HabitListAdapter(habits)
//                    recyclerView.adapter = adapter
//                }
                .addOnSuccessListener { documents ->
                    habits = documents.toObjects<SocialHabitDocument>().toMutableList()
                    habits.forEachIndexed { index, habit ->
                        //val habitId = documents.documents[index].id // Get the document ID

                        //habit.id = habitId // Assign the habitId to the habit's id property
                        habit.id = documents.documents[index].id
                    }
                    adapter = HabitListAdapter(habits)
                    recyclerView.adapter = adapter
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error retrieving habits", exception)
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
                itemView.setOnLongClickListener {
                    val habit = habits[adapterPosition]
                    showDeleteConfirmationDialog(habit)
                    true
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
            } else {
                Toast.makeText(this, "Invalid progress value", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun updateHabitInFirestore(habit: SocialHabitDocument) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val habitRef = firestore.collection("futurehabits").document(habit.id)
           // val sanitizedId = habit.id.replace("/", "").replace(".", "")
           // val habitRef = firestore.collection("futurehabits").document(sanitizedId)
            val updates = mapOf("progress" to habit.progress)
            habitRef.update(updates)
                .addOnSuccessListener {
                    Log.d(TAG, "Habit updated successfully")
                    // Update the habit in the list
                    val updatedHabitIndex = habits.indexOfFirst { it.id == habit.id }
                    if (updatedHabitIndex != -1) {
                        habits[updatedHabitIndex] = habit
                        adapter.notifyItemChanged(updatedHabitIndex)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error updating habit", exception)
                }
        }
    }





    fun addNewSHabit(view: View) {
        startActivity(Intent(this, FutureYou::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HabitDevide::class.java))
        finish()
    }

    private fun showDeleteConfirmationDialog(habit: SocialHabitDocument) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Habit")
        builder.setMessage("Are you sure you want to delete this habit?")

        builder.setPositiveButton("Delete") { _, _ ->
            deleteHabitFromFirestore(habit)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun deleteHabitFromFirestore(habit: SocialHabitDocument) {
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        if (userId != null) {
//            val habitRef = firestore.collection("futurehabits").document(habit.id)
//            habitRef.delete()
//                .addOnSuccessListener {
//                    Log.d(TAG, "Habit deleted successfully")
//                }
//                .addOnFailureListener {
//                    Log.e(TAG, "Error deleting habit", it)
//                }
//        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val habitRef = firestore.collection("futurehabits").document(habit.id)
            habitRef.delete()
                .addOnSuccessListener {
                    Log.d(TAG, "Habit deleted successfully")
                    // Remove the habit from the list
                    val deletedHabitIndex = habits.indexOfFirst { it.id == habit.id }
                    if (deletedHabitIndex != -1) {
                        habits.removeAt(deletedHabitIndex)
                        adapter.notifyItemRemoved(deletedHabitIndex)
                    }
                }
                .addOnFailureListener {
                    Log.e(TAG, "Error deleting habit", it)
                }
        } else {
            Log.e(TAG, "User ID is null")
        }
    }
}
