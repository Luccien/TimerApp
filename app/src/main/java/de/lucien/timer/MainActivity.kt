package de.lucien.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource



class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = Color(0xFF101010),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    //contentAlignment = Alignment.Center
                ) {
                    Timer(
                        //totalTime = 100L * 1000L,
                        //totalTime = 10L * 1000L,
                        totalTime = 13L * 1000L,

                        handleColor = Color.Green,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFF37B900),
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }
    }


}





    @Composable
    fun Timer(
        totalTime: Long,
        handleColor: Color,
        inactiveBarColor: Color,
        activeBarColor: Color,
        modifier: Modifier = Modifier,
        initialValue: Float = 1f,
        strokeWidth: Dp = 5.dp
    ) {

        var counter by remember {
            mutableStateOf(10) // 10 is number of pictures
        }
        var timeDelay by remember {
            mutableStateOf(totalTime / 10)// 10 is number of pictures
        }

        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        var value by remember {
            mutableStateOf(initialValue)
        }
        var currentTime by remember {
            mutableStateOf(totalTime)
        }
        var isTimerRunning by remember {
            mutableStateOf(false)
        }



        LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
            if (currentTime > 0 && isTimerRunning) {
                delay(timeDelay)
               currentTime -= timeDelay
                counter--

                value = currentTime / totalTime.toFloat()
            }
        }
       /* Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .onSizeChanged {
                    size = it
                }*/
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {




            TimerCountTextfield(currentTime=currentTime)

            ButtonStartPause(
                currentTime=currentTime,
                onCurrentTimeChange={currentTime = it},
                totalTime=totalTime,
                counter=counter,
                onCounterChange={counter = it},
                isTimerRunning,
                onIsTimerRunningChange ={isTimerRunning = it},
            //    modifier=Modifier
            )


            ///////////////
            Button(
                onClick = {
                    currentTime = totalTime
                    counter = 10
                    isTimerRunning = false
                },
                //modifier = Modifier.align(Alignment.BottomEnd),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =   Color.Green

                )
            ) {
                Text(
                    text = "Reset"
                )
            }



        }// end box
    }



@Composable
fun TimerCountTextfield(currentTime:Long,){
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

}


@Composable
fun ButtonStartPause(
                     currentTime:Long,
                     onCurrentTimeChange:(Long) -> Unit,
                     totalTime: Long,
                     counter:Int,
                     onCounterChange:(Int) -> Unit,
                     isTimerRunning:Boolean,
                     onIsTimerRunningChange:(Boolean) -> Unit,
                     //modifier:Modifier = Modifier
                     //modifier: Modifier
) {

    //Box() {
    ///////////////
    Button(
        onClick = {
            if (currentTime <= 0L) {
                onCurrentTimeChange(totalTime)
                onCounterChange(10)
                onIsTimerRunningChange(true)

            } else {
                onIsTimerRunningChange(!isTimerRunning)
            }
        },
        // modifier = Modifier.align(Alignment.BottomCenter),
        //modifier = modifier.align(Alignment.BottomCenter),

        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                Color.Green
            } else {
                Color.Red
            }
        )
    ) {
        Text(
            text = if (isTimerRunning && currentTime > 0L) "Pause"
            else if (!isTimerRunning && currentTime == totalTime) "Start"
            else if (!isTimerRunning && currentTime > 0L) "Continue"
            else "Restart"
        )
    //}
    /////////////////////////////
}

}

@Composable
fun ShowCard(currentTime:Long){
    Card(
        elevation = 4.dp,
    ) {
        if(currentTime > 12000L){
            Image(
                painter = painterResource(id = R.drawable.hund),
                contentDescription = null
            )}
        else if(currentTime > 11000L){
            Image(
                painter = painterResource(id = R.drawable.hund),
                contentDescription = null
            )}
        else{
            Image(
                painter = painterResource(id = R.drawable.hund),
                contentDescription = null
            )}
    }


}
