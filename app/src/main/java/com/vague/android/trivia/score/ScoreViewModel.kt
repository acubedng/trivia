package com.vague.android.trivia.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(in_score: Int) : ViewModel() {

    private val _score = MutableLiveData<Int>(in_score)
    val score: LiveData<Int>
        get() = _score

}