package com.example.serene

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.createBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.jour.MVVM.JourViewModel
//import com.example.jour.MVVM.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.FileOutputStream

import android.provider.ContactsContract
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.firestore.Query

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_journal_add_edit_note.*
import kotlinx.android.synthetic.main.journal_note_card.*
import kotlinx.android.synthetic.main.activity_create_account.*
//import kotlinx.android.synthetic.main.activity_journal_main*
import java.time.chrono.ChronoLocalDateTime


//data class Note(
////    val dateTime:String="",
//    val title: String="",
////    val description:String=""
//)
//
//class  JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class JournalMainActivity: AppCompatActivity()
//    val db = Firebase.firestore
////   lateinit var jourRV: RecyclerView
////lateinit var user : String
////    lateinit var addButton: FloatingActionButton
//////    lateinit var viewModel: JourViewModel
////override fun onCreate(savedInstanceState: Bundle?) {
////    super.onCreate(savedInstanceState)
////    setContentView(R.layout.activity_journal_main)
////    jourRV= findViewById(R.id.jourRecyclerView)
////    addButton=findViewById(R.id.jourAddButton)
////    jourRV.layoutManager=LinearLayoutManager(this)
////    //RVAdapter
////    val jourRVAdapter = JourRVAdapter(this,this,this)
////    jourRV.adapter = jourRVAdapter
////    viewModel= ViewModelProvider(
////    this,
////    ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(JourViewModel::class.java)
////    viewModel.allEntries.observe(this, Observer { list->
////        list?.let {
////            jourRVAdapter.updateList(it)
////        }
////
////    })
////
////    addButton.setOnClickListener{
////        val intent = Intent(this@JournalMainActivity,JournalMainActivity::class.java)
////        startActivity(intent)
////        this.finish()
////    }
////
////}
////
////override fun onDeleteIconClick(note: Note) {
////    viewModel.deleteNote(note)
////    Toast.makeText(this,"${note.jourTitle} was deleted", Toast.LENGTH_SHORT).show()
////
////}
////
////override fun onNoteClick(note: Note) {
////    //Passing Bitmap:
////    val filename ="bitmap.png"
////    try{
////        val stream: FileOutputStream=this.openFileOutput(filename, Context.MODE_PRIVATE)
////        val bmp: Bitmap? = note.jourImage
////        bmp?.compress(Bitmap.CompressFormat.PNG,100,stream)
////        //Clean Up:
////        stream.close()
////        bmp?.recycle()
////
////        val intent = Intent(this@JournalMainActivity,JournalAddEditNoteActivity::class.java)
////        intent.putExtra("noteType", "Edit")
////        intent.putExtra("noteTitle", note.jourTitle)
////        intent.putExtra("noteDescription", note.jourDescription)
////        intent.putExtra("noteID",note.id)
////        intent.putExtra("noteImage",filename)
////        startActivity(intent)
////        this.finish()
////
////    }catch (e: Exception){
////        e.printStackTrace()
////    }
////
////
////}
////
////}
////}
////lateinit var dateTextView: EditText
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_journal_main)
//
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {}
//    val query = db.collection("journal").whereEqualTo("user",FirebaseAuth.getInstance().currentUser!!.uid).orderBy("datetime", Query.Direction.DESCENDING)
//    val options = FirestoreRecyclerOptions.Builder<JournalMainActivity>().setQuery(query,JournalMainActivity::class.java::class.java)
//        .setLifecycleOwner(this).build()
//    val adapter = object : FirestoreRecyclerAdapter<JournalMainActivity, JournalViewHolder>(options) {
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
//            val view = LayoutInflater.from(this@JournalMainActivity)
//                .inflate(android.R.layout.simple_list_item_2, parent, false)
//            return JournalViewHolder(view)
//        }
//         fun onBindViewHolder(holder: JournalViewHolder, position: Int, model: Note) {
//            val tvDate: TextView = holder.itemView.findViewById(android.R.id.text1)
////            val tvDate: TextView = holder.itemView.findViewById(android.R.id.text2)
////            tvStatus.text = model.dateTime
//            tvDate.text = model.title
////            tvDate.text=model.description
//
//            tvDate.setTextColor(Color.parseColor("#000000"))
//            tvDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
//
////            tvStatus.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
////            tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
////        setContentView(R.layout.journal_note_card)
////    user = intent.getStringExtra("user").toString()
////        FirebaseFirestore.getInstance().collection("journal").document(user.toString()).get().addOnSuccessListener {
////            dateTextView.text = it.data!!.getValue("date").toString()
////            editTitle.text = it.data!!.getValue("title").toString()
////            descTextView.text = it.data!!.getValue("description").toString()
//////            txt_address.text = it.data!!.getValue("address").toString()
////
////        }.addOnFailureListener{
////            Toast.makeText(applicationContext,"Cannot Get Data from Server", Toast.LENGTH_LONG).show()
////
////            startActivity(Intent(this, JournalAddEditNoteActivity::class.java))
////            finish()
////        }
////
////    }
//        }
//
//        override fun onBindViewHolder(
//            holder: JournalViewHolder,
//            position: Int,
//            model: JournalMainActivity
//        ) {
//            TODO("Not yet implemented")
//        }
//    }}
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, Home::class.java))
//        finish()
//    }
//
//    fun goToUpdateProfile(view: View){
//        startActivity(Intent(this, UpdateProfile::class.java))
//        finish()
//    }
//
//}
//
////private fun Any.setQuery(query: Query, java: Class<Class<note>>): Any {
////
////}
