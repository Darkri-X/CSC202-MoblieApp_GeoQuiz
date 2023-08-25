package com.example.geoquiz_app

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    // The list of questions with their corresponding answers (true for correct, false for incorrect).
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    // Index to keep track of the current question being displayed.
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId


    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }


    fun moveToPrevious() {
        currentIndex = if (currentIndex > 0) currentIndex - 1 else questionBank.size - 1
    }
    // P.162: SaveStateHandle API can be used to store and retrieve i
    // Instance state and persist that information even if your process is killed
    // Activity.onSaveInstanceState(Bundle) == Similar to Activity.onPause(), Activity.onStop()
}
