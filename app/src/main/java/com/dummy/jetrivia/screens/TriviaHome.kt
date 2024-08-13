package com.dummy.jetrivia.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dummy.jetrivia.component.Questions


@Composable
fun TriviaHome(viewModal: QuestionViewModal = hiltViewModel()) {
    Questions(viewModal)
}