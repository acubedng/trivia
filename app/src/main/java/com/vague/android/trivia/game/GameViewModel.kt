package com.vague.android.trivia.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _questionTime = MutableLiveData<Long>()
    val questionTimeString: LiveData<String>
        get() = Transformations.map(_questionTime) { timeLong ->
            DateUtils.formatElapsedTime(timeLong)
        }

    private val countDownTimer = object : CountDownTimer(QUESTION_TIME, SECOND) {
        override fun onFinish() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onTick(p0: Long) {
            _questionTime.value = p0 / SECOND
        }
    }

    init {
        startCountDownTimer()
    }

    private fun startCountDownTimer() {
        stopCountDownTimer() // Stop if already running
        countDownTimer.start()
    }

    private fun stopCountDownTimer() {
        countDownTimer.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        stopCountDownTimer()
    }

    companion object {
        const val SECOND: Long = 1000
        const val QUESTION_TIME: Long = 4000
    }
}