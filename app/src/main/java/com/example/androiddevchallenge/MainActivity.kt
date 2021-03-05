/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.components.AnimatedCircle
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                CounterdownTimer()
            }
        }
    }
}

// Start building your app here!
@Composable
fun CounterdownTimer() {
    val isCountDownActive = remember { mutableStateOf(false) }
    var timerValue = remember { mutableStateOf(10000L) }

    val timer = object : CountDownTimer(timerValue.value, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            timerValue.value = millisUntilFinished
        }

        override fun onFinish() {
            cancel()
            timerValue.value = 10000L
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        Canvas(modifier = Modifier) {
//            val size = Size(300f, 300f)
//            val canvasWidth = size.width
//            val canvasHeight = size.height
//            drawCircle(
//                color = Color.Blue,
//                center = Offset(0f, y = canvasHeight * -1),
//                radius = size.minDimension / 4
//            )
//        }
        AnimatedCircle(
            modifier = Modifier,
            isCountDownActive.value
        )
        Text(
            text = (timerValue.value / 1000).toString(),
            style = TextStyle(color = Color.Red, fontSize = 50.sp)
        )
        Button(
            onClick = {
                if (isCountDownActive.value) {
                    timer.cancel()
                } else {
                    timer.start()
                }
                isCountDownActive.value = !isCountDownActive.value
            }
        ) {
            Text(
                text = if (isCountDownActive.value) {
                    "Stop"
                } else {
                    "Start"
                }
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        CounterdownTimer()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        CounterdownTimer()
    }
}
