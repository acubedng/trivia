package com.vague.android.trivia.game

import java.util.*

class QuestionsGenerator {

    //TODO: Add more questions
    private val questionsList = mutableListOf(
        "What programming language was created by JetBrains™ in 2011?",
        "What is a LifeCycleOwner?",
        "What is LiveData?"
    )

    fun getNextQuestion(): String? {
        if (questionsList.size == 0) return null

        val randNum = Random().nextInt(questionsList.size)
        val currentQuestion = questionsList[randNum]
        questionsList.removeAt(randNum)
        return currentQuestion
    }
}