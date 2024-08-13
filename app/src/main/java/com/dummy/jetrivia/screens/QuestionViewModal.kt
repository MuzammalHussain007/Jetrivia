package com.dummy.jetrivia.screens

import android.provider.ContactsContract.Data
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dummy.jetrivia.data.QuestionItem
import com.dummy.jetrivia.network.DataOrException
import com.dummy.jetrivia.repositry.QuestionRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModal @Inject constructor(private var respo : QuestionRepositry) : ViewModel() {
      val data: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        getAllQuestion()
    }

    private fun getAllQuestion() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = respo.getAllQuestion()
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }
}