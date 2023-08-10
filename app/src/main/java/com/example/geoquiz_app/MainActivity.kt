package com.example.geoquiz_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.geoquiz_app.databinding.ActivityMainBinding

/**
 * This is the main activity class for the GeoQuiz app.
 * It allows users to answer true/false questions and provides feedback on correctness.
 */
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // The list of questions with their corresponding answers (true for correct, false for incorrect).
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    // Index to keep track of the current question being displayed.
    private var currentIndex = 0

    /**
     * Called when the activity is created. Sets up the UI and event listeners.
     * @param savedInstanceState The saved instance state, if any.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up event listeners for True and False buttons to check user answers.
        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }

        // Set up event listener for the Next button to display the next question.
        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        // Set up event listener for the Previous button to display the previous question.
        binding.previousButton.setOnClickListener {
            currentIndex = if (currentIndex > 0) currentIndex - 1 else questionBank.size - 1
            updateQuestion()
        }

        // Display the first question when the activity is created.
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    /**
     * Updates the question displayed on the UI.
     */
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    /**
     * Checks the user's answer against the correct answer and displays a Toast message with the result.
     * @param userAnswer The user's answer (true or false).
     */
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        // Determine the appropriate message to display based on correctness.
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        // Display the Toast message with the result.
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}
