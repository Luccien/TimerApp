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
import androidx.compose.ui.text.font.FontWeight
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
                       /* handleColor = Color.Green,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Color(0xFF37B900),
                        modifier = Modifier.size(200.dp)*/
                    )
                }
            }
        }
    }
}





    @Composable
    fun Timer(
        //totalTime: Long,
        /*handleColor: Color,
        inactiveBarColor: Color,
        activeBarColor: Color,
        modifier: Modifier = Modifier,
        //initialValue: Float = 1f,
        strokeWidth: Dp = 5.dp*/
    ) {

        val pictureAmount = 11-1 // one less than actual pictures are there

        var totalTime by remember {
            mutableStateOf(12L * 1000L)
        }

        var counter by remember {
            mutableStateOf(pictureAmount)
        }

/*
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }

 */
        /*
        var value by remember {
            mutableStateOf(initialValue)
        }*/
        var currentTime by remember {
            mutableStateOf(totalTime)
        }
        var isTimerRunning by remember {
            mutableStateOf(false)
        }
        var timeToShowNextPicture by remember {
            var timePerPicture = totalTime/pictureAmount
            mutableStateOf(counter * timePerPicture)
        }


        LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
            if (currentTime > 0 && isTimerRunning) {
                delay(1000L)
                currentTime -= 1000L
                //value = currentTime / totalTime.toFloat()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {




            if (isTimerRunning && currentTime > 0L) {}
            else if (!isTimerRunning && currentTime == totalTime) {SetTimeForTimer(
                currentTime = currentTime,
                onCurrentTimeChange = { currentTime = it },
                totalTime = totalTime,
                onTotalTimeChange = { totalTime = it },
                counter = counter,
                onCounterChange = { counter = it },
                isTimerRunning,
                onIsTimerRunningChange = { isTimerRunning = it },
            )}
            else if (!isTimerRunning && currentTime > 0L) {}
            else {SetTimeForTimer(
                currentTime = currentTime,
                onCurrentTimeChange = { currentTime = it },
                totalTime = totalTime,
                onTotalTimeChange = { totalTime = it },
                counter = counter,
                onCounterChange = { counter = it },
                isTimerRunning,
                onIsTimerRunningChange = { isTimerRunning = it },
            )}

            /*
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
*/



            //--------
            if (isTimerRunning && currentTime > 0L) {
                TimerCountTextfield(currentTime = currentTime)
            }
           else if (!isTimerRunning && currentTime == totalTime) {}
           else if (!isTimerRunning && currentTime > 0L){
               TimerCountTextfield(currentTime = currentTime)
           }
            //------



            //------
            ShowHangman(
                pictureAmount = pictureAmount,
                currentTime = currentTime,
                totalTime = totalTime,
                counter = counter,
                onCounterChange = { counter = it },
                timeToShowNextPicture = timeToShowNextPicture,
                onTimeToShowNextPictureChange = { timeToShowNextPicture = it })
            //------


            Row() { // --row
                /////////////////
                if(!isTimerRunning ) {
                    ButtonStartPause( // IS ONLY START BUTTON NOW!!!
                        currentTime = currentTime,
                        onCurrentTimeChange = { currentTime = it },
                        totalTime = totalTime,
                        counter = counter,
                        onCounterChange = { counter = it },
                        isTimerRunning,
                        onIsTimerRunningChange = { isTimerRunning = it },
                        onTimeToShowNextPictureChange = { timeToShowNextPicture = it },
                        pictureAmount = pictureAmount
                    )
                }
                if(isTimerRunning ) {
                    ResetButton(
                        currentTime = currentTime,
                        onCurrentTimeChange = { currentTime = it },
                        totalTime = totalTime,
                        counter = counter,
                        onCounterChange = { counter = it },
                        isTimerRunning,
                        onIsTimerRunningChange = { isTimerRunning = it },
                        onTimeToShowNextPictureChange = { timeToShowNextPicture = it },
                        pictureAmount = pictureAmount
                    )
                }

            }//--row
        }
    }



@Composable
fun ShowHangman(pictureAmount:Int,
                currentTime: Long,
                totalTime: Long,
                counter: Int,
                onCounterChange: (Int) -> Unit,
                timeToShowNextPicture:Long,
                onTimeToShowNextPictureChange: (Long) -> Unit)
{
// counts down


    if(currentTime <= timeToShowNextPicture ){
        if( (counter-1) >=  0) { // 2 - 1 >= 1 // 2 ist letzter counter wert

            /////////////////
            val timePerPicture = totalTime/pictureAmount
            //val timeToShowNextPicture = counter * timePerPicture
            onTimeToShowNextPictureChange( (counter-1) * timePerPicture)
            //ShowCard(counter-1)
            onCounterChange(counter - 1)
            /////////////////
        }
    } // 11 -2 // 1 er abgezogen // aber nicht sofort --> 1 ist -- 11 ist letzter
    ShowCard(pictureNumber=pictureAmount - counter,currentTime=currentTime,totalTime=totalTime)
}



@Composable
fun ShowCard(pictureNumber:Int,currentTime:Long,totalTime:Long){
    Card(
        elevation = 4.dp,
    ) {
        if(pictureNumber == 0){
            Image(
                painter = painterResource(id = R.drawable.p0),
                contentDescription = null
            )}
        if(pictureNumber == 1){
            Image(
                painter = painterResource(id = R.drawable.p1),
                contentDescription = null
            )}
        else if(pictureNumber == 2){
            Image(
                painter = painterResource(id = R.drawable.p2),
                contentDescription = null
            )}
        else if(pictureNumber == 3){
            Image(
                painter = painterResource(id = R.drawable.p3),
                contentDescription = null
            )}
        else if(pictureNumber == 4){
            Image(
                painter = painterResource(id = R.drawable.p4),
                contentDescription = null
            )}
        else if(pictureNumber == 5){
            Image(
                painter = painterResource(id = R.drawable.p5),
                contentDescription = null
            )}
        else if(pictureNumber == 6){
            Image(
                painter = painterResource(id = R.drawable.p6),
                contentDescription = null
            )}
        else if(pictureNumber == 7){
            Image(
                painter = painterResource(id = R.drawable.p7),
                contentDescription = null
            )}
        else if(pictureNumber == 8){
            Image(
                painter = painterResource(id = R.drawable.p8),
                contentDescription = null
            )}
        else if(pictureNumber == 9){
            Image(
                painter = painterResource(id = R.drawable.p9),
                contentDescription = null
            )}
        else if(pictureNumber == 10){
            if (currentTime == 0L) {
                Image(
                    painter = painterResource(id = R.drawable.p11),
                    contentDescription = null
                )
            }
            else{
                Image(
                    painter = painterResource(id = R.drawable.p10),
                    contentDescription = null
                )
            }

        }

    }
}







@Composable
fun ResetButton(
                currentTime:Long,
                onCurrentTimeChange:(Long) -> Unit,
                totalTime: Long,
                counter:Int,
                onCounterChange:(Int) -> Unit,
                isTimerRunning:Boolean,
                onIsTimerRunningChange:(Boolean) -> Unit,
                onTimeToShowNextPictureChange: (Long) -> Unit,
                pictureAmount:Int,)

{

    Button(
        onClick = {
            onCurrentTimeChange(totalTime)
            onCounterChange(pictureAmount)
            onIsTimerRunningChange(false)

            //////////////
            val timePerPicture = totalTime/pictureAmount
            onTimeToShowNextPictureChange( (pictureAmount) * timePerPicture)
            ///////////////

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
                    val timeChange =totalTime + (1000L*60)
                    onTotalTimeChange(timeChange)
                    onCurrentTimeChange(timeChange)
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
                    val timeChange =totalTime + (1000L)
                    onTotalTimeChange(timeChange)
                    onCurrentTimeChange(timeChange)
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
                        onCurrentTimeChange(newTime)
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
                        onCurrentTimeChange(newTime)
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

    // TODO --- STRING wenn länge kurz dann + 0

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
                     onTimeToShowNextPictureChange: (Long) -> Unit,
                     pictureAmount:Int,)

{

    //if(!isTimerRunning )

    Button(
        onClick = {
            if (currentTime <= 0L) {
                onCurrentTimeChange(totalTime)
                onCounterChange(pictureAmount)
                onIsTimerRunningChange(true)
                //////////////
                val timePerPicture = totalTime/pictureAmount
                onTimeToShowNextPictureChange( (pictureAmount) * timePerPicture)
                ///////////////

            } else {
                if(!isTimerRunning){onIsTimerRunningChange(true)}
                //onIsTimerRunningChange(!isTimerRunning) --KEEP OLD VERSION
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
            text = if (isTimerRunning && currentTime > 0L) "Pause" // PAUSE IS NOT NEEDED
            else if (!isTimerRunning && currentTime == totalTime) "Start"
            else if (!isTimerRunning && currentTime > 0L) "Continue" // CONTINUE IS NOT NEEDED
            else "Restart"
        )
        //trace("gfh")

        //---------

        /*
        if (isTimerRunning && currentTime > 0L) {}
        else if (!isTimerRunning && currentTime == totalTime) {}
        else if (!isTimerRunning && currentTime > 0L)
        else {

            //---------
            onCurrentTimeChange(totalTime)
            onCounterChange(pictureAmount)
            onIsTimerRunningChange(false)

            //////////////
            val timePerPicture = totalTime/pictureAmount
            onTimeToShowNextPictureChange( (pictureAmount) * timePerPicture)
            //------------


            ///////////////
            //Log.DEBUG("istimerrunning " + !isTimerRunning + "  creenttime  " + currentTime)
            //Log.d("tag", "istimerrunning " + !isTimerRunning + "  creenttime  " + currentTime)
        }

         */
        //-----------

    }
}


