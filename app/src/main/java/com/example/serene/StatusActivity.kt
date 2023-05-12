package com.example.serene
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.serene.MainTasks
import com.example.serene.Profile
import com.example.serene.R
import com.example.serene.chatbot.BotActivity
import com.example.serene.expert_support.ExpertSupportMain
import com.example.serene.history.HistoryMain
import com.example.serene.montly_quiz.QuizIntro
import com.example.serene.moods.SelectMood
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

//
//
//class StatusActivity: AppCompatActivity() {
//
//
//    lateinit var viewPager: ViewPager
//    lateinit var adapter: ImagePagerAdapter
//    val images = listOf(R.drawable.na6, R.drawable.na8, R.drawable.na9, R.drawable.na10)
//    var currentIndex = 0
//    val handler = Handler()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        try {
//            this.supportActionBar!!.hide()
//        } catch (e: NullPointerException) {
//        }
//        setContentView(R.layout.activity_status)
//
//        viewPager = findViewById(R.id.viewPager_h)
//        adapter = ImagePagerAdapter(images)
//        viewPager.adapter = adapter
//
//        val progressBar = findViewById<ProgressBar>(R.id.progressBarS)
//        progressBar.max = images.size
//        progressBar.progress = currentIndex + 1
//        progressBar.progressDrawable = ContextCompat.getDrawable(this, R.drawable.progress_bar_custom)
//
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                currentIndex++
//                if (currentIndex >= images.size) {
//                    currentIndex = 0
//                }
//                viewPager.setCurrentItem(currentIndex, true)
//                handler.postDelayed(this, 20000) // 20 second delay
//            }
//        }, 20000) // 20 second delay for first image
//
//
//
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        handler.removeCallbacksAndMessages(null)
//    }
//
//
//
//    // Adapter for the ViewPager
//    inner class ImagePagerAdapter(val images: List<Int>) : PagerAdapter() {
//        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            val imageView = ImageView(container.context)
//            imageView.setImageResource(images[position])
//            imageView.setOnClickListener {
//                if (currentIndex < images.size - 1) {
//                    currentIndex++
//                } else {
//                    currentIndex = 0
//                }
//                viewPager.setCurrentItem(currentIndex, true)
//            }
//            container.addView(imageView)
//            return imageView
//        }
//
//        override fun getCount() = images.size
//
//        override fun isViewFromObject(view: View, obj: Any) = view == obj
//
//        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
//            container.removeView(obj as View)
//        }
//
//    }
//}
class StatusActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var adapter: ImagePagerAdapter
    val images = listOf(R.drawable.na6, R.drawable.na8, R.drawable.na9, R.drawable.na10)
    var currentIndex = 0
    val handler = Handler()
    var progress: ProgressBar? = null
    var progressStatus = 0
    var timeDuration = 20000 // 20 seconds
    var isPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.activity_status)

        viewPager = findViewById(R.id.viewPager_h)
        adapter = ImagePagerAdapter(images)
        viewPager.adapter = adapter

        progress = findViewById(R.id.progressBarS)
        progress!!.max = timeDuration
        progress!!.progress = 0

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (!isPaused) {
                    progressStatus += 1000
                    progress!!.progress = progressStatus
                    if (progressStatus >= timeDuration) {
                        currentIndex++
                        if (currentIndex >= images.size) {
                            currentIndex = 0
                        }
                        viewPager.setCurrentItem(currentIndex, true)
                        progressStatus = 0
                        progress!!.progress = progressStatus
                    }
                }
                handler.postDelayed(this, 1000) // 1 second delay
            }
        }, 1000) // 1 second delay for first image
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onPause() {
        super.onPause()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
    }

    // Adapter for the ViewPager
    inner class ImagePagerAdapter(val images: List<Int>) : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(container.context)
            imageView.setImageResource(images[position])
            imageView.setOnClickListener {
                if (currentIndex < images.size - 1) {
                    currentIndex++
                } else {
                    currentIndex = 0
                }
                viewPager.setCurrentItem(currentIndex, true)
                progressStatus = 0
                progress!!.progress = progressStatus
            }
            container.addView(imageView)
            return imageView
        }

        override fun getCount() = images.size

        override fun isViewFromObject(view: View, obj: Any) = view == obj

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }
}
