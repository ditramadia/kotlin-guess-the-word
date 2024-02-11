package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        Log.i("GameFragment", "ViewModelProvider called")
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
        })
        viewModel.isGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinish()
            }
        })

        return binding.root
    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment()
        action.setScore(viewModel.score.value ?: 0)
        findNavController().navigate(action)
    }
}