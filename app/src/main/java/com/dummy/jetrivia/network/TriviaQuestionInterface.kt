package com.dummy.jetrivia.network

import com.dummy.jetrivia.data.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface TriviaQuestionInterface {

    @GET("world.json")
   suspend fun getQuestion() : Question
}