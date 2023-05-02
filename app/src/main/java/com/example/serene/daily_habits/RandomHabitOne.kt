package com.example.serene.daily_habits

import android.content.Intent
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.serene.R
import java.util.*
import java.util.concurrent.TimeUnit

class RandomHabitOne : AppCompatActivity() {

    private lateinit var countdownTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var countdownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 0
    private var isTimerRunning = false

    private lateinit var soundPool: SoundPool
    private var alarmSoundId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_habit_random_one)

        countdownTextView = findViewById(R.id.countdown_textview)
        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)

        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        alarmSoundId = soundPool.load(this, R.raw.gong, 1)

        startButton.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            }
        }

        stopButton.setOnClickListener {
            if (isTimerRunning) {
                stopTimer()
            }
        }
    }

    private fun startTimer() {
        timeLeftInMillis =  1200000 // 20 minutes in milliseconds
        countdownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountdownText()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountdownText()
                playSoundEffect()
                showSuccessMessage()
            }
        }.start()

        isTimerRunning = true
        startButton.isEnabled = false
        stopButton.isEnabled = true
    }

    private fun stopTimer() {
        countdownTimer.cancel()
        isTimerRunning = false
        startButton.isEnabled = true
        stopButton.isEnabled = false
        updateCountdownText()
    }

    private fun updateCountdownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        countdownTextView.text = timeLeftFormatted
    }

    private fun playSoundEffect() {
        soundPool.play(alarmSoundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    private fun showSuccessMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Congratulations! You've completed the task.")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DailyHabitsMain::class.java))
        finish()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        soundPool.release()
//        soundPool.unregisterAudioCallback(null)
//    }
}
