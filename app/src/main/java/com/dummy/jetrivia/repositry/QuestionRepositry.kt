package com.dummy.jetrivia.repositry

import com.dummy.jetrivia.data.QuestionItem
import com.dummy.jetrivia.network.DataOrException
import com.dummy.jetrivia.network.TriviaQuestionInterface
import javax.inject.Inject

class QuestionRepositry @Inject constructor(var api: TriviaQuestionInterface) {

    private val dataOrException =
        DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()


    suspend fun getAllQuestion(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {

        try {
            dataOrException.loading = true
            dataOrException.data = api.getQuestion()

            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }

        } catch (e: Exception) {
            dataOrException.exception = e
        }
        return dataOrException

    }
}