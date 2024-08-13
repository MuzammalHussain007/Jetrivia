package com.dummy.jetrivia.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dummy.jetrivia.data.QuestionItem
import com.dummy.jetrivia.screens.QuestionViewModal
import com.dummy.jetrivia.util.appColor.AppColor

@Composable
fun Questions(viewModal: QuestionViewModal) {
    val question = viewModal.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableStateOf<Int>(0)
    }

    val questions = question?.get(questionIndex.value)

    if (question != null) {
        QuestionPreview(question = questions!!, questionIndex, viewModal) {
            questionIndex.value += 1
        }
    }
    Log.d("question", "Questions: ${question?.size} ")
}


//@Preview
@Composable
fun QuestionPreview(
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModal,
    onNextClicked: (Int) -> Unit = {}
) {
    val choiceState = remember(question) {
        question.choices.toMutableList()
    }
    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val corectAnsState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    val answerUpdate: (Int) -> Unit = remember(question) {
        { it: Int ->
            answerState.value = it
            corectAnsState.value = choiceState[it] == question.answer
        }
    }



    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = AppColor.DarkPurple
    ) {

        Column(
            Modifier.padding(4.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            QuestionTracker(questionIndex.value,)

            DottedLine(
                androidx.compose.ui.graphics.PathEffect.dashPathEffect(
                    floatArrayOf(
                        10f,
                        10f
                    ), 0f
                )
            )


            Text(
                text = question.question,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = AppColor.OffWhite,
                lineHeight = 22.sp,
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .padding(12.dp)
                    .align(Alignment.Start)
            )

            choiceState.forEachIndexed { index, answerText ->

                Row(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .border(
                            width = 4.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(AppColor.OffDarkPurple, AppColor.OffDarkPurple)
                            ),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(
                            RoundedCornerShape(
                                topStartPercent = 50,
                                topEndPercent = 50,
                                bottomEndPercent = 50,
                                bottomStartPercent = 50
                            )
                        )
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = (answerState.value == index),
                        onClick = {
                            answerUpdate(index)
                        },
                        modifier = Modifier.padding(start = 16.dp),
                        colors = RadioButtonDefaults.colors(
                            if (corectAnsState.value == true && index == answerState.value) {
                                Color.Green.copy(0.2f)
                            } else {
                                Color.Red.copy(0.2f)
                            }
                        )
                    )

                    Column {
                        val annotatedString = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Light,
                                    color =
                                    if (corectAnsState.value == true && index == answerState.value) {
                                        Color.Green.copy(0.2f)
                                    } else if (corectAnsState.value == false && index == answerState.value) {
                                        Color.Red.copy(0.2f)
                                    } else {
                                        AppColor.OffWhite
                                    }
                                )
                            ) {
                                append(answerText)
                            }

                        }

                        Text(text = "$annotatedString", modifier = Modifier.padding(6.dp))
                    }
                }

            }


            Button(
                onClick = { onNextClicked(questionIndex.value) },
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(34.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColor.LightBlue,
                    contentColor = AppColor.OffWhite
                )
            ) {
                Text(
                    text = "Next",
                    modifier = Modifier.padding(4.dp),
                    color = AppColor.OffWhite,
                    fontSize = 17.sp
                )

            }

        }

    }
}


@Composable
fun DottedLine(pathEffect: androidx.compose.ui.graphics.PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            AppColor.LightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )

    }
}


@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100) {
    Text(text = buildAnnotatedString {
        withStyle(ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                SpanStyle(
                    color = AppColor.LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp
                )
            ) {
                append("Question $counter/")
                withStyle(
                    SpanStyle(
                        color = AppColor.LightGray,
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                ) {
                    append("$outOf")

                }
            }
        }
    }, modifier = Modifier.padding(20.dp))
}

@Preview
@Composable
fun ShowProgress()
{
    Row(modifier = Modifier.padding(3.dp).fillMaxWidth().height(45.dp)) {

    }
}