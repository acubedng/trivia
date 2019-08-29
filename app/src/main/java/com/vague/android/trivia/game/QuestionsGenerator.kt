package com.vague.android.trivia.game

import java.util.*

class QuestionsGenerator {

    //TODO: Add more questions
    private val questionsList = mutableListOf(
        "What programming language was created by JetBrains™ in 2011?",
        "What is a LifeCycleOwner?",
        "What is LiveData?",
        "What is Context"
    ).apply {
        addAll(
            """What is an Activity?
                |What method you should override to use Android’s menu which is placed on the action bar?
                |What is the name of the class used by Intent to store additional information?
            """.trimMargin().split("\n")
        )
    }

    val totalQuestions = questionsList.size

    fun getNextQuestion(): String? {
        if (questionsList.size == 0) return null

        val randNum = Random().nextInt(questionsList.size)
        val currentQuestion = questionsList[randNum]
        questionsList.removeAt(randNum)
        return currentQuestion
    }
}