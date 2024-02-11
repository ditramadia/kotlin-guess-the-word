package com.example.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

class ScoreViewModel(finalScore: Int) : ViewModel() {

    // The play again state
    private val _isPlayAgain = MutableLiveData<Boolean>()
    var isPlayAgain : LiveData<Boolean>
        set(value) {_isPlayAgain}
        get() = _isPlayAgain

    // The final score
    private val _score = MutableLiveData<Int>()
    var score : LiveData<Int>
        set(value) {_score}
        get() = _score

    init {
        Log.i("ScoreViewModel", "Final score is ${finalScore}")
        _score.value = finalScore
    }

    fun onPlayAgain() {
        _isPlayAgain.value = true
    }

    fun onPlayAgainComplete() {
        _isPlayAgain.value = false
    }
}