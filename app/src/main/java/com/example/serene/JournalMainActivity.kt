package com.example.serene


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_journal_main.*

import java.util.*


data class Note(
   // val datetime:Date,
    val date: String = "",
    val title: String="",
   val description:String="",
//val date: HijrahDate
)

class  JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class JournalMainActivity: AppCompatActivity() {
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal_main)
//        try {
//            this.supportActionBar!!.setBackgroundDrawable(
//                ColorDrawable(
//                    getResources()
//                        .getColor(R.color.main)
//                )
//            )
//        } catch (e: NullPointerException) {
        supportActionBar?.title = getString(R.string.journal_main)
//        }
        val query = db.collection("journal")
            .whereEqualTo("user", FirebaseAuth.getInstance().currentUser!!.uid)
            .orderBy("datetime", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Note>().setQuery(query, Note::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<Note, JournalViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
                val view = LayoutInflater.from(this@JournalMainActivity)
                   // .inflate(android.R.layout.simple_list_item_2, parent, false)
                    .inflate(R.layout.journal_note_card, parent, false)
                return JournalViewHolder(view)
            }



            override fun onBindViewHolder(holder: JournalViewHolder, position: Int, model: Note) {


                val tvTitle: TextView = holder.itemView.findViewById(R.id.editTitle)
                val tvDate: TextView = holder.itemView.findViewById(R.id.dateTextView)
                val tvDescription: TextView = holder.itemView.findViewById(R.id.descTextView)

                tvDate.text = model.date
                tvTitle.text = model.title
                tvDescription.text = model.description

                tvDate.setTextColor(Color.parseColor("#FFFFFF"))
                tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)

                tvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)



                val documentId = snapshots.getSnapshot(position).id

                holder.itemView.setOnClickListener {
                    changePageToJournalSingleView(documentId)
                }
            }
        }
        jourRecyclerView.adapter = adapter
      jourRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun changePageToJournalSingleView(docID: String){
        val intent = Intent(this, JournalSingleView::class.java)
        intent.putExtra("docID", docID)
        startActivity(intent)
    }
    fun addNewJournal(view: View){
        startActivity(Intent(this, JournalAddEditNoteActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainTasks::class.java))
        finish()
    }
}




