package com.example.serene.expert_support

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serene.*
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_expert_list_view.*
import kotlinx.android.synthetic.main.activity_journal_main.*

data class Expert(
    // val datetime:Date,
    val name: String = "",
)
class  ExpertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class ExpertList: AppCompatActivity() {
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert_list_view)
        supportActionBar?.title = getString(R.string.m_e)

        val query = db.collection("expert")
        val options = FirestoreRecyclerOptions.Builder<Expert>().setQuery(query, Expert::class.java)
            .setLifecycleOwner(this).build()
        val adapter = object : FirestoreRecyclerAdapter<Expert, ExpertViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpertViewHolder {
                val view = LayoutInflater.from(this@ExpertList)
                    // .inflate(android.R.layout.simple_list_item_2, parent, false)
                    .inflate(R.layout.activity_expert_card, parent, false)
                return ExpertViewHolder(view)
            }
            override fun onBindViewHolder(holder: ExpertViewHolder, position: Int, model: Expert) {

                val tvName: TextView = holder.itemView.findViewById(R.id.item_tv_name)
//                val tvDate: TextView = holder.itemView.findViewById(R.id.dateTextView)
//                val tvDescription: TextView = holder.itemView.findViewById(R.id.descTextView)

             //   tvDate.text = model.date
                tvName.text = model.name
             //   tvDescription.text = model.description

//                tvDate.setTextColor(Color.parseColor("#FFFFFF"))
//                tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
//
//                tvTitle.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
//                tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)




                val documentId = snapshots.getSnapshot(position).id

                holder.itemView.setOnClickListener {
                    changePageToExpertSingleView(documentId)
                }
            }
        }
        expertRecyclerView.adapter = adapter
        expertRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    fun changePageToExpertSingleView(docID: String){
        val intent = Intent(this, ExpertSingleView::class.java)
        intent.putExtra("docID", docID)
        startActivity(intent)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,ExpertSupportMain::class.java))
        finish()
    }
//    fun addNewJournal(view: View){
//        startActivity(Intent(this, JournalAddEditNoteActivity::class.java))
//        finish()
//    }
}




