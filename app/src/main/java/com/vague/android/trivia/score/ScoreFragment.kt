package com.vague.android.trivia.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.vague.android.trivia.R
import com.vague.android.trivia.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        val scoreArgs: ScoreFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this, ScoreViewModelFactory(scoreArgs.score)).get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}