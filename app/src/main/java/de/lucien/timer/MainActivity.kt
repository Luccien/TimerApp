package de.lucien.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.lucien.timer.ui.theme.TimerAppTheme
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

//import java.util.*

class MainActivity : ComponentActivity() {





/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting("Android")
                }
            }
        }
    }

*/

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerAppTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp)
                ) {
                    //////////
                    Card(
                        elevation = 4.dp,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hund),
                            contentDescription = null
                        )
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text("AB CDE", fontWeight = FontWeight.W700)
                            Text("+0 12345678")
                            Text("XYZ city", fontWeight = FontWeight.W300)
                        }
                    }

                    ///////
                }
            }
        }
    }
}
*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                color = Color(0xFF101010),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center
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

        /*
        Column(modifier = Modifier.padding(10.dp)) {
            Text("AB CDE", fontWeight = FontWeight.W700)
            Text("+0 12345678")
            Text("XYZ city", fontWeight = FontWeight.W300)
        }*/
    }
//}


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

                //---------------
                delay(timeDelay)
                //delay(100L)
                //currentTime -= 100L
                currentTime -= timeDelay
                counter--
                //-------------------

                value = currentTime / totalTime.toFloat()
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .onSizeChanged {
                    size = it
                }
        ) {

           /*
            Canvas(modifier = modifier) {
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = activeBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f * value,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                val center = Offset(size.width / 2f, size.height / 2f)
                val beta = (250f * value + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(beta) * r
                val b = sin(beta) * r
                drawPoints(
                    listOf(Offset(center.x + a, center.y + b)),
                    pointMode = PointMode.Points,
                    color = handleColor,
                    strokeWidth = (strokeWidth * 3f).toPx(),
                    cap = StrokeCap.Round
                )
            }
            */
            //-------------
            //------------------------->>>>>> PUT IN   ShowCard(currentTime)
//-------------
            Text(
                text = (counter).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            /*
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )*/
            Button(
                onClick = {
                    if (currentTime <= 0L) {
                        //currentTime = 13L * 1000L
                        currentTime = totalTime
                        counter = 10
                        isTimerRunning = true
                    } else {
                        isTimerRunning = !isTimerRunning
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                    //backgroundColor = if (!isTimerRunning ) {
                    // -- works backgroundColor = if (currentTime <= 0L) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )
            ) {
                Text(
                    text = if (isTimerRunning && currentTime > 0L) "Pause"
                    //else if (!isTimerRunning && currentTime > 0L) "Start"
                    else if (!isTimerRunning && currentTime == totalTime) "Start"
                    else if (!isTimerRunning && currentTime > 0L) "Continue"

                    // text = if (isTimerRunning && currentTime >= 0L) "Stop1"
                   // else if (!isTimerRunning && currentTime >= 0L) "Start1"
                    else "Restart"
                )
            }
        }
    }





/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimerAppTheme {
        Greeting("Android")
    }
}

 */