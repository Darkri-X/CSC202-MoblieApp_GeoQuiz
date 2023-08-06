package com.example.geoquiz_app

import androidx.annotation.StringRes

/**
 * Represents a question in the GeoQuiz app.
 * @param textResId The resource ID of the question text.
 * @param answer The correct answer to the question (true for correct, false for incorrect).
 */
data class Question(@StringRes val textResId: Int, val answer: Boolean)
