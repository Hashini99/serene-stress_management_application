package com.example.serene.montly_quiz

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.serene.Home

import com.example.serene.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class MQuiz : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var answerRadioGroup: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView
    private var questionIndex = 0
    private var answers = IntArray(10)
    private var allQuestionsAnswered = false
    private val questionList = arrayOf(
        "In the last month, how often have you been upset because of something that happened unexpectedly?",
        "In the last month, how often have you felt that you were unable to control the important things in your life?",
        "In the last month, how often have you felt nervous and “stressed”?",
        "In the last month, how often have you felt confident about your ability to handle your personal problems?",
        "In the last month, how often have you felt that things were going your way?",
        "In the last month, how often have you found that you could not cope with all the things that you had to do?",
        "In the last month, how often have you been able to control irritations in your life?",
        "In the last month, how often have you felt that you were on top of things?",
        "In the last month, how often have you been angered because of things that were outside of your control?",
        "In the last month, how often have you felt difficulties were piling up so high that you could not overcome them?"
    )
    private val reverseQuestions = arrayOf(3, 4, 6, 7)

    private fun reverseAnswer(answerText: String): Int {
        return when (answerText) {
            "Never" -> 4
            "Almost Never" -> 3
            "Sometimes" -> 2
            "Fairly Often" -> 1
            "Very Often" -> 0
            else -> -1
        }
    }
    private lateinit var progressBar: ProgressBar
    private val answerOptions = arrayOf("Never", "Almost Never", "Sometimes", "Fairly Often", "Very Often")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_que)

        questionTextView = findViewById(R.id.question_text_view)
        answerRadioGroup = findViewById(R.id.answer_radio_group)
        submitButton = findViewById(R.id.submit_button)
        resultTextView = findViewById(R.id.result_text_view)
        progressBar = findViewById(R.id.progress_bar)

        setQuestion()

        submitButton.setOnClickListener {
            val selectedAnswerId = answerRadioGroup.checkedRadioButtonId
            if (selectedAnswerId != -1) {
                val selectedAnswer = findViewById<RadioButton>(selectedAnswerId)
                answers[questionIndex] =
                    if (reverseQuestions.contains(questionIndex))
                        reverseAnswer(selectedAnswer.text.toString())
                    else
                        answerOptions.indexOf(selectedAnswer.text.toString())
                answerRadioGroup.clearCheck()
                questionIndex++
                if (questionIndex == questionList.size) {
                    calculateResult()
                    submitButton.isEnabled = false
                    allQuestionsAnswered = true
                } else {
                    setQuestion()
                }
            } else {
                Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun setQuestion() {
        questionTextView.text = questionList[questionIndex]
        answerRadioGroup.removeAllViews()
        val answerOptions =
            if (reverseQuestions.contains(questionIndex))
                arrayOf("Very Often", "Fairly Often", "Sometimes", "Almost Never", "Never")
            else
                arrayOf("Never", "Almost Never", "Sometimes", "Fairly Often", "Very Often")
        for (i in answerOptions.indices) {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = answerOptions[i]
            answerRadioGroup.addView(radioButton)
        }
        // Update progress bar
        progressBar.incrementProgressBy(1)


    }


    private fun calculateResult() {
        for (i in reverseQuestions.indices) {
            val reverseIndex = reverseQuestions[i]
            answers[reverseIndex] = 5 - answers[reverseIndex] // subtract from 5 instead of 4
        }
        var pssScore = 0
        for (i in answers.indices) {
            val answerValue = answers[i]
            pssScore += when (i) {
                in 0..4 -> answerValue
                in 5..9 -> answerValue - 1
                in 10..16 -> answerValue - 2
                else -> 0
            }
        }
        val interpretation = when (pssScore) {
            in 0..17 -> "You are facing Low stress"
            in 18..29 -> "You are facing Moderate stress"
            in 30..43 -> "You are facing  High stress"
            else -> "Error"
        }
//        println("PSS Score: $pssScore")
//        println("Interpretation: $interpretation")
//        resultTextView.text = "PSS Score: $pssScore \nInterpretation: $interpretation"
        println("PSS Score: $pssScore")
        println("Interpretation: $interpretation")
        resultTextView.text = "PSS Score: $pssScore\n\n $interpretation"
        resultTextView.setTextColor(Color.BLACK)


        // Access Firestore instance
        val db = Firebase.firestore

        // Create a new document with a generated ID
        val docRef = db.collection("quiz").document()

        // Create a map with the PSS score and current timestamp
        val data = hashMapOf(
            "user" to FirebaseAuth.getInstance().currentUser?.uid,
            "score" to pssScore,
            "datetime" to Timestamp.now(),
            "date" to "${Calendar.getInstance().get(Calendar.YEAR)}-${Calendar.getInstance().get(Calendar.MONTH)+1}-${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}",
            "time" to "${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}",
            "intro" to interpretation
        )

        // Add the data to the document
        docRef.set(data)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: ${docRef.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, Home::class.java))
        finish()
    }

}