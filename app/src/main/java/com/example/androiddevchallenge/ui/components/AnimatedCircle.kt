package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private enum class AnimatedCircleProgress { START, END, READY }

@Composable
fun AnimatedCircle(
    modifier: Modifier = Modifier,
    isCountDownActive: Boolean
) {
    var currentState: MutableTransitionState<AnimatedCircleProgress>
    if (isCountDownActive) {
        currentState = remember {
            MutableTransitionState(AnimatedCircleProgress.START)
                .apply { targetState = AnimatedCircleProgress.END }
        }
    } else {
        currentState = remember {
            MutableTransitionState(AnimatedCircleProgress.READY)
        }
    }
    val transition = updateTransition(currentState)
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 10000,
                easing = LinearEasing
            )
        }
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else if (progress == AnimatedCircleProgress.READY) {
            0f
        } else {
            360f
        }
    }

    Canvas(modifier) {
        drawArc(
            color = Color.Blue,
            startAngle = 0f,
            sweepAngle = 360f - angleOffset,
            topLeft = Offset(-100f, -200f),
            size = Size(200f, 200f),
            useCenter = false,
            style = Stroke(10.dp.toPx())
        )
    }
}

@Preview
@Composable
fun AnimatedCircle() {
}