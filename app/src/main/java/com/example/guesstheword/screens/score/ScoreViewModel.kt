package com.example.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    init {
        Log.i("ScoreViewModel", "Final score is ${finalScore}")
    }
}