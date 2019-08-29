package com.vague.android.trivia.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _questionNum = MutableLiveData<Int>(1)
    val questionNum: LiveData<Int>
        get() = _questionNum

    private val _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private val _questionTime = MutableLiveData<Long>()
    val questionTimeString: LiveData<String>
        get() = Transformations.map(_questionTime) { timeLong ->
            DateUtils.formatElapsedTime(timeLong)
        }

    private val _currentQuestion = MutableLiveData<String>()
    val currentQuestion: LiveData<String>
        get() = _currentQuestion

    private val countDownTimer = object : CountDownTimer(QUESTION_TIME, SECOND) {
        override fun onFinish() {
            setNextQuestion()
        }

        override fun onTick(p0: Long) {
            _questionTime.value = p0 / SECOND
        }
    }

    private val _gameFinished = MutableLiveData<Boolean>(false)
    val gameFinished: LiveData<Boolean>
        get() = _gameFinished

    private val questionsGenerator = QuestionsGenerator()

    val totalQuestions = questionsGenerator.totalQuestions

    init {
        _currentQuestion.value = questionsGenerator.getNextQuestion()
        startCountDownTimer()
    }

    private fun setNextQuestion() {
        val nextQuestion = questionsGenerator.getNextQuestion()
        if (nextQuestion == null) {
            _gameFinished.value = true
            stopCountDownTimer()
        } else {
            _currentQuestion.value = nextQuestion
            _questionNum.value = _questionNum.value?.plus(1)
            startCountDownTimer()
        }
    }

    fun gameFinishedHandled() {
        _gameFinished.value = false
    }

    fun correct() {
        _score.value = _score.value?.plus(1)
        setNextQuestion()
    }

    fun skip() {
        setNextQuestion()
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
        const val QUESTION_TIME: Long = 15000

        const val TAG = "GameViewModel"
    }
}