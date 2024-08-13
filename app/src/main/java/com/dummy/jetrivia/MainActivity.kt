package com.dummy.jetrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dummy.jetrivia.data.Question
import com.dummy.jetrivia.screens.QuestionViewModal
import com.dummy.jetrivia.screens.TriviaHome
import com.dummy.jetrivia.ui.theme.JetriviaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetriviaTheme {
               Surface(color = MaterialTheme.colorScheme.background) {
                   TriviaHome()
               }
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetriviaTheme {

    }
}