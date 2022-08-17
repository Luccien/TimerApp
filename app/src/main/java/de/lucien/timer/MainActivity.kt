package de.lucien.timer

import android.media.MediaPlayer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           Surface(
                color = Color(0xFF101010),
           ) {
               Timer()
                }
        }
    }
}




    @Preview
    @Composable
    fun Timer(
    ) {

        // Fetching the local context
        val mContext = LocalContext.current

        // Declaring and Initializing
        // the MediaPlayer to play "audio.mp3"
        val mMediaPlayer = MediaPlayer.create(mContext, R.raw.bells)



        val pictureAmount = 11-1 // one less than actual pictures are there

        var totalTime by remember {
            mutableStateOf(1L * 60L * 1000L) // 1 minutes timer
        }

        var counter by remember {
            mutableStateOf(pictureAmount)
        }

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

            }
        }


        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(

               modifier = Modifier
                   .fillMaxHeight(0.75f)
                   .fillMaxWidth(),

               horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround    //.SpaceEvenly // TODO CHANGE
            ) {


                if (isTimerRunning) {
                    //------
                    ShowHangman(
                        pictureAmount = pictureAmount,
                        currentTime = currentTime,
                        totalTime = totalTime,
                        counter = counter,
                        onCounterChange = { counter = it },
                        timeToShowNextPicture = timeToShowNextPicture,
                        onTimeToShowNextPictureChange = { timeToShowNextPicture = it },
                        mMediaPlayer = mMediaPlayer
                        )

                    //------
                } else {
                    /////
                    Card(
                        elevation = 4.dp,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pleer),
                            contentDescription = null
                        )
                    }
                    ///////
                }

                Row() { // --row
                    /////////////////
                    if (!isTimerRunning) {
                        ButtonStartPause( // IS ONLY START BUTTON NOW!!!
                            currentTime = currentTime,
                            onCurrentTimeChange = { currentTime = it },
                            totalTime = totalTime,
                            onCounterChange = { counter = it },
                            isTimerRunning,
                            onIsTimerRunningChange = { isTimerRunning = it },
                            onTimeToShowNextPictureChange = { timeToShowNextPicture = it },
                            pictureAmount = pictureAmount
                        )
                    }

                    if (isTimerRunning) {
                        ResetButton(
                            onCurrentTimeChange = { currentTime = it },
                            totalTime = totalTime,
                            onCounterChange = { counter = it },
                            onIsTimerRunningChange = { isTimerRunning = it },
                            pictureAmount = pictureAmount,
                            mMediaPlayer = mMediaPlayer
                        )
                    }

                }//--row


            }// column end
            //-------


            // SECOND COLUMN
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (!isTimerRunning && currentTime == totalTime) {
                    SetTimeForTimer(
                        onCurrentTimeChange = { currentTime = it },
                        totalTime = totalTime,
                        onTotalTimeChange = { totalTime = it },

                    )
                }
                //------

                Box(modifier = Modifier.padding(40.dp)) {
                    //--------
                    if (isTimerRunning && currentTime > 0L) {
                        TimerCountTextfield(currentTime = currentTime)
                    } else if (!isTimerRunning && currentTime == totalTime) {
                    } else if (!isTimerRunning && currentTime > 0L) {
                        TimerCountTextfield(currentTime = currentTime)
                    }
                    //------
                }// end box
            } // end // SECOND COLUMN
        }
    }



@Composable
fun ShowHangman(pictureAmount:Int,
                currentTime: Long,
                totalTime: Long,
                counter: Int,
                onCounterChange: (Int) -> Unit,
                timeToShowNextPicture:Long,
                onTimeToShowNextPictureChange: (Long) -> Unit,
                mMediaPlayer : MediaPlayer)
{

    if(currentTime <= timeToShowNextPicture ){
        if( (counter-1) >=  0) { // 2 - 1 >= 1 // 2 si last counter value

            /////////////////
            val timePerPicture = totalTime/pictureAmount
            onTimeToShowNextPictureChange( (counter-1) * timePerPicture)
            onCounterChange(counter - 1)
            /////////////////
        }
    } // 11 -2 // 1 er abgezogen // aber nicht sofort --> 1 ist -- 11 ist letzter
    ShowCard(pictureNumber=pictureAmount - counter,currentTime=currentTime,totalTime=totalTime, mMediaPlayer=mMediaPlayer)
}



@Composable
fun ShowCard(pictureNumber:Int,currentTime:Long,totalTime:Long,
             mMediaPlayer : MediaPlayer){
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
                mMediaPlayer.start()
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
                onCurrentTimeChange:(Long) -> Unit,
                totalTime: Long,
                onCounterChange:(Int) -> Unit,
                onIsTimerRunningChange:(Boolean) -> Unit,
                pictureAmount:Int,
                mMediaPlayer : MediaPlayer)
{
    Button(
        onClick = {

            onCurrentTimeChange(totalTime)
            onCounterChange(pictureAmount)
            onIsTimerRunningChange(false)
            mMediaPlayer.pause()

        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White

        )
    ) {
        Text(
            text = "Reset"
        )
    }

}




@Composable
fun TimerCountTextfield(currentTime:Long){
    Row(
    ) {
        showTimeInMinAndSec(currentTime)
    }

}






@Composable
fun SetTimeForTimer(
                    onCurrentTimeChange:(Long) -> Unit,
                    totalTime: Long,
                    onTotalTimeChange:(Long) -> Unit,

){
    Column() {
        Row() {
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {
                    // add one minute
                    val timeChange =totalTime + (1000L*60)
                    onTotalTimeChange(timeChange)
                    onCurrentTimeChange(timeChange)
                },colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),modifier = Modifier.size(width = 40.dp,height = 40.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_upward_24),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = {
                    // subtract one minute
                    val timeChange =totalTime + (1000L)
                    onTotalTimeChange(timeChange)
                    onCurrentTimeChange(timeChange)
                },colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),modifier = Modifier.size(width = 40.dp,height = 40.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_upward_24),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp)
                )
            }
        }
        Row() {
           showTimeInMinAndSec(totalTime)

        }
        Row() {
            Spacer(modifier = Modifier.size(5.dp))
            Button(
                onClick = {
                    // add one second
                    val newTime = totalTime - (60 * 1000L)
                    if(newTime>0) {
                        onTotalTimeChange(newTime)
                        onCurrentTimeChange(newTime)
                    }
                },colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),modifier = Modifier.size(width = 40.dp,height = 40.dp)

            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                    contentDescription = "",
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.size(25.dp))
            Button(
                onClick = {
                    // subtract one second
                    val newTime = totalTime - (1000L)
                    if(newTime>0) {
                        onTotalTimeChange(newTime)
                        onCurrentTimeChange(newTime)
                    }
                },colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),modifier = Modifier.size(width = 40.dp,height = 40.dp)

            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                    contentDescription = "",
                    modifier = Modifier.size(5.dp)
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


    var minSt:String = min.toString()
    var secLeftSt:String = secLeft.toString()

    if(minSt.length ==1){
        minSt ="0" + minSt
    }
    else if(minSt.length ==0){
        minSt ="00"
    }
    if(secLeftSt.length ==1){
        secLeftSt ="0" + secLeftSt
    }

    Text(
        text = minSt,
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
        text = secLeftSt,
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
                     onCounterChange:(Int) -> Unit,
                     isTimerRunning:Boolean,
                     onIsTimerRunningChange:(Boolean) -> Unit,
                     onTimeToShowNextPictureChange: (Long) -> Unit,
                     pictureAmount:Int,)

{

    Button(
        onClick = {
            if (currentTime <= 0L) { // this is for continue
                //----------
                onCurrentTimeChange(totalTime)
                onCounterChange(pictureAmount)
                onIsTimerRunningChange(true)
                //////////////
                val timePerPicture = totalTime/pictureAmount
                onTimeToShowNextPictureChange( (pictureAmount) * timePerPicture)
                ///////////////
                //-----------

            } else {
                if(!isTimerRunning){
                    onIsTimerRunningChange(true)
                    //----------
                    onCurrentTimeChange(totalTime)
                    onCounterChange(pictureAmount)
                    //////////////
                    val timePerPicture = totalTime/pictureAmount
                    onTimeToShowNextPictureChange( (pictureAmount) * timePerPicture)
                    ///////////////
                    //-----------

                }
            }
        },


        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                Color.White
            } else {
                Color.White // not used
            }
        )
    ) {
        Text(
            text = if (isTimerRunning && currentTime > 0L) "Pause" // PAUSE IS NOT NEEDED
            else if (!isTimerRunning && currentTime == totalTime) "Start"
            else if (!isTimerRunning && currentTime > 0L) "Continue" // CONTINUE IS NOT NEEDED
            else "Restart"
        )


    }
}


