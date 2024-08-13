package com.dummy.jetrivia.di

import com.dummy.jetrivia.data.Question
import com.dummy.jetrivia.network.TriviaQuestionInterface
import com.dummy.jetrivia.repositry.QuestionRepositry
import com.dummy.jetrivia.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TriviaAppModule {

    @Singleton
    @Provides
    fun provideQuestionApi() : TriviaQuestionInterface
    {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(TriviaQuestionInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionRepositry(api : TriviaQuestionInterface) = QuestionRepositry(api)
}