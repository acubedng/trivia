package com.vague.android.trivia.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vague.android.trivia.R
import com.vague.android.trivia.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        binding.titleBtnStartgame.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }

        return binding.root
    }
}