package com.example.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    companion object {
        const val DONE = 0L
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 60000L
    }

    // The Countdown timer
    private var timer : CountDownTimer

    // The current time
    private var _currentTime = MutableLiveData<Long>()
    var currentTime : LiveData<Long>
        set(value) {_currentTime}
        get() = _currentTime

    // The current word
    private var _word = MutableLiveData<String>()
    var word : LiveData<String>
        set(value) {_word}
        get() = _word

    // The current score
    private var _score = MutableLiveData<Int>()
    var score : LiveData<Int>
        set(value) {_score}
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    // The game finished state
    private var _isGameFinish = MutableLiveData<Boolean>()
    var isGameFinish : LiveData<Boolean>
        set(value) {_isGameFinish}
        get() = _isGameFinish

    init {
        Log.i("GameViewModel", "GameViewModel created!")
        _score.value = 0
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _isGameFinish.value = true
            }
        }
        timer.start()

        _isGameFinish.value = false
        resetList()
        _word.value = wordList[0]
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
             resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinish() {
        _isGameFinish.value = false
    }
}