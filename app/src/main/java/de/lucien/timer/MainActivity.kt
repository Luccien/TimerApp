package de.lucien.timer

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
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
                ) {
                    Timer(
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
        //totalTime: Long,
        handleColor: Color,
        inactiveBarColor: Color,
        activeBarColor: Color,
        modifier: Modifier = Modifier,
        initialValue: Float = 1f,
        strokeWidth: Dp = 5.dp
    ) {

        var totalTime by remember {
            mutableStateOf(13L * 1000L) // 10 is number of pictures
        }

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

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            if ((!isTimerRunning && currentTime == totalTime) || (isTimerRunning && currentTime <= 0L)) {
                SetTimeForTimer(
                    currentTime = currentTime,
                    onCurrentTimeChange = { currentTime = it },
                    totalTime = totalTime,
                    onTotalTimeChange = { totalTime = it },
                    counter = counter,
                    onCounterChange = { counter = it },
                    isTimerRunning,
                    onIsTimerRunningChange = { isTimerRunning = it },
                )
            }

            TimerCountTextfield(currentTime = currentTime)



            Row() { // --row
                /////////////////
                ButtonStartPause(
                    currentTime = currentTime,
                    onCurrentTimeChange = { currentTime = it },
                    totalTime = totalTime,
                    counter = counter,
                    onCounterChange = { counter = it },
                    isTimerRunning,
                    onIsTimerRunningChange = { isTimerRunning = it }

                )
                /////////////// TODO
               // if(!isTimerRunning && currentTime == 0){

                //}
                //else {
                  //  if ((isTimerRunning && currentTime != totalTime)) {  // TODO


                        ResetButton(currentTime = currentTime,
                            onCurrentTimeChange = { currentTime = it },
                            totalTime = totalTime,
                            counter = counter,
                            onCounterChange = { counter = it },
                            isTimerRunning,
                            onIsTimerRunningChange = { isTimerRunning = it })


                    //}
                    ///////////////////
                //}
            }//--row
        }
    }

@Composable
fun ResetButton(currentTime:Long,
                onCurrentTimeChange:(Long) -> Unit,
                totalTime: Long,
                counter:Int,
                onCounterChange:(Int) -> Unit,
                isTimerRunning:Boolean,
                onIsTimerRunningChange:(Boolean) -> Unit){
    // TODO
    Button(
        onClick = {
            onCurrentTimeChange(totalTime)
            onCounterChange(10)
            onIsTimerRunningChange(false)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Green

        )
    ) {
        Text(
            text = "Reset"
        )
    }

}





// end reset button

/*
@Composable
fun ResetButton(
    currentTime: Long,
    onCurrentTimeChange: () -> Unit,
    totalTime: Long,
    counter: Int,
    onCounterChange: () -> Unit,
    timerRunning: Boolean,
    onIsTimerRunningChange: () -> Unit
) {
    TODO("Not yet implemented")
}
*/

@Composable
fun TimerCountTextfield(currentTime:Long){
    Row() {
        showTimeInMinAndSec(currentTime)
    }

}






@Composable
fun SetTimeForTimer(currentTime:Long,
                    onCurrentTimeChange:(Long) -> Unit,
                    totalTime: Long,
                    onTotalTimeChange:(Long) -> Unit,
                    counter:Int,
                    onCounterChange:(Int) -> Unit,
                    isTimerRunning:Boolean,
                    onIsTimerRunningChange:(Boolean) -> Unit,
){
    Column() {
        Row() {
            Button(
                onClick = {
                    // add one minute
                    onTotalTimeChange(totalTime + (1000L*60) )
                }) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_upward_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
            Button(
                onClick = {
                    // subtract one minute
                    onTotalTimeChange(totalTime + (1000L) )
                }) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_upward_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Row() {
           showTimeInMinAndSec(totalTime)

        }
        Row() {
            Button(
                onClick = {
                    // add one second
                    val newTime = totalTime - (60 * 1000L)
                    if(newTime>0) {
                        onTotalTimeChange(newTime)
                    }
                }) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
            Button(
                onClick = {
                    // subtract one second
                    val newTime = totalTime - (1000L)
                    if(newTime>0) {
                        onTotalTimeChange(newTime)
                    }
                }) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

    }
}

@Composable
fun showTimeInMinAndSec(timeInMilliS:Long){
    val allSec = timeInMilliS/1000L
    val min:Int = (allSec/60).toInt()
    val secLeft:Long = allSec - (min*60)

    Text(
        text = min.toString(),
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    Text(
        text = ":",
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    Text(
        text = secLeft.toString(),
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

) {

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
        //trace("gfh")

        //---------
        if (isTimerRunning && currentTime > 0L) "Pause"
        else if (!isTimerRunning && currentTime == totalTime) "Start"
        else if (!isTimerRunning && currentTime > 0L)
        else {
            //Log.DEBUG("istimerrunning " + !isTimerRunning + "  creenttime  " + currentTime)
            Log.d("tag", "istimerrunning " + !isTimerRunning + "  creenttime  " + currentTime)
        }
        //-----------
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
