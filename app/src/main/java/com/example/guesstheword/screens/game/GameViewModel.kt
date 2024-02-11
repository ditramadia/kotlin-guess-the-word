package com.example.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

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
        _isGameFinish.value = false
        resetList()
        _word.value = wordList[0]
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
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
             _isGameFinish.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
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