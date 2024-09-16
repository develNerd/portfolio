import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastSumBy
import kotlinx.coroutines.delay
import kotlin.math.*
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ToolIndicator() {
    Canvas(
        modifier = Modifier.size(width = 100.dp, height = 5.dp),
        onDraw = {
            drawRect(brush = Brush.horizontalGradient(brushText))
        }
    )
}


@Composable
fun CircleBrush() {
    Canvas(
        modifier = Modifier.size(146.dp),
        onDraw = {
            translate(top = -100f) {
                drawCircle(Brush.horizontalGradient(brush))
            }
        }
    )
}

@Composable
fun LeftCornerBrush() {
    Canvas(
        modifier = Modifier.size(100.dp),
        onDraw = {
            translate(left = -100f) {
                drawCircle(Brush.horizontalGradient(brush))
            }
        }
    )
}

@Composable
fun LeftCornerArc() {
    Canvas(
        modifier = Modifier.size(120.dp),
        onDraw = {
            
            translate(left = 80f) {
                drawCircle(Brush.horizontalGradient(leftCornerBrush))
            }

        }
    )
}

val points:MutableList<Offset> = mutableListOf()
val dirs:MutableList<String> = mutableListOf()
val changes = mutableListOf<PointerInputChange>()
val rotations = mutableListOf<Float>()

data class Project(
    val title: String,
    val description: String,
    val link: String? = "",
    val image: String? = "",
)

val projects = listOf(
    Project(
        title = "ChatGPT-MULTIPLATFORM",
        description = "ChatGPT Multiplatform aims to implement all clients for CHAT GPT i.e Mobile(Android and IOS), Desktop and Web using Kotlin Multiplatform/Mobile",
        link = "https://github.com/develNerd/ChatGPT-Multiplatform",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "WebRTC-Android @ Telnyx",
        description = "WebRTC-Android is a library that allows you to make WebRTC calls from Android using Telnyx WebRTC SDK",
        link = "https://github.com/team-telnyx/telnyx-webrtc-android",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "WebRTC-iOS @ Telnyx",
        description = "WebRTC-iOS is a library that allows you to make WebRTC calls from iOS using Telnyx WebRTC SDK",
        link = "https://github.com/team-telnyx/telnyx-webrtc-ios",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "Flutter Voice SDK @ Telnyx",
        description = "Flutter Voice SDK is a library that allows you to make Voice calls from Flutter App (ios & android) using Telnyx Voice SDK",
        link = "https://github.com/team-telnyx/flutter-voice-sdk/tree/main/android",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "Savit Authenticator",
        description = "Savit Authenticator is an open source Authenticator App built with Jetpack Compose deployed using Jenkins CI, built to Encourage the use Offline Time / Counter Based One Time Passwords. (This project made some references to the Open Source Google Authenticator project)"
                ,
        link = "https://github.com/develNerd/ChatGPT-Multiplatform",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "Space Jam",
        description = "SpaceJam is a Jetpack Compose app, built on top of clean-architecture and is featuring graphql, sqldelight, canvas api/custom UIs, navigation, material3 and many more :). SpaceJam uses SpaceX's open sourced api written with GraphQl. Enjoy all Spacex's past launches, ships, launches, next launches and many more with spacejam. NB : Still in dev.",
        link = "https://github.com/develNerd/SpaceJam",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "Android 12 Animation Clone",
        description = "This project is a clone of the Android 12 Clock Animation in the settings app. It helps provide in depth knowledge about Android's Touch Input Geometry in General.You learn how to implement Rotation related stuff using Jetpack compose with touch events" ,
        link = "https://github.com/develNerd/AnimatingCompoables",
        image = "drawable/telnyx.png"
    ),
    Project(
        title = "Therapeutic",
        description = "The app provides content having genres that relate to how our users can reduce or deal with mental health situations. These include videos on meditation, self awareness etc." ,
        link = "https://github.com/develNerd/Therapeutic",
        image = "drawable/telnyx.png"
    ),
)

@Composable
fun Projects(onProject: (Int) -> Unit = {}){

    var rotationValue by remember {
        mutableFloatStateOf(0f)
    }

    var isSelected by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(!isSelected) {
        while(true) {
            delay(100.milliseconds)
            rotationValue += 1f
            if (isSelected) {
                rotationValue -= 20f
                break
            }
        }
    }

    val rotation = animateFloatAsState(targetValue = rotationValue, label = "",animationSpec = if (!isSelected) spring(100f) else spring(10f))


    Box(modifier = Modifier.wrapContentSize()
        , contentAlignment = Alignment.Center) {
        StarLayout(radius = 300.dp, modifier = Modifier .pointerInput(Unit) {



            detectDragGestures { change, _ ->
                change.pressed
                change.consume()
                changes.add(change)






                val now =  change.position
                val prev = change.previousPosition

                // Log.d("StarActivity", " dragAmount: ${change.position} ${change.previousPressed}"

                points.add(prev)
                points.add(now)

                if (changes.size > 10) {
                    isSelected = true
                    val rotation = calculateRotation(changes)
                    rotations.add(rotation)
                    if (rotations.size > 1){
                        if (rotations.sum() > 0) {
                            rotationValue += 45f
                        } else {
                            rotationValue -= 45f
                            println("StarActivity Counterclockwise")
                        }
                        rotations.clear()

                    }
                    println("StarActivity rotation: $rotationValue")
                    changes.clear()
                }
            }

        }.rotate(rotation.value)) {
            projects.forEachIndexed { index, project ->

                Box(modifier = Modifier
                    .size(150.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(100))

                    .background(if ((index + 1).mod(2) == 1) Brush.linearGradient(
                        colors = leftCornerBrush
                    ) else Brush.linearGradient(
                        colors = brushText
                    ), shape = RoundedCornerShape(100))
                    .clickable {
                        onProject(index)

                    }, contentAlignment = Alignment.Center
                ) {

                    Box(modifier = Modifier.rotate(-rotation.value).padding(10.dp), contentAlignment = Alignment.Center) {
                        Text(text = project.title, color = Color.White, fontWeight = Bold, textAlign = TextAlign.Center)
                    }


                }

            }


        }

    }

}

@Composable
fun StarLayout(
    radius: Dp,
    modifier: Modifier = Modifier,
    drawStyle: DrawStyle = Stroke(12f),
    color: Color = Color.Yellow,
    content: @Composable () -> Unit
) {
    var starRadiusPx = with(LocalDensity.current) { radius.roundToPx() }
    var count = 0
    var totalRadius = 0
    var maxChildDiameter = 0
    // Use the Layout composable to create the custom layout
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        //measure child nodes to get a list of placeables
        val placeables = measurables.map { it.measure(constraints) }
        // set the count variable value to be equal to children count
        count = placeables.size
        // find the diameter of the largest child so we can set the layout size properly
        placeables.forEach {
            val h = it.height.toDouble()
            val w = it.width.toDouble()
            val diameter = sqrt(h * h + w * w)
            if (diameter > maxChildDiameter) maxChildDiameter = diameter.toInt()
        }
        // set the total radius to be the sum of star radius and half the diameter of largest child
        totalRadius = starRadiusPx + maxChildDiameter/2


        // set layout size to be twice the total radius in width and height
        layout(totalRadius * 2, totalRadius * 2) {
            val step = PI * 2 / placeables.size
            var angle = 0.0
            // place child elements
            placeables.forEach {
                it.place(
                    totalRadius - it.width / 2 + (starRadiusPx * cos(angle)).toInt(),
                    totalRadius - it.height / 2 + (starRadiusPx * sin(angle)).toInt(),
                )
                angle += step
            }
        }
    }
}

private fun Offset.angle(): Float =
    if (x == 0f && y == 0f) 0f else -atan2(x, y) * 180f / PI.toFloat()


fun calculateCentroid(
    changes: List<PointerInputChange>,
    useCurrent: Boolean = true
): Offset {
    var centroid = Offset.Zero
    var centroidWeight = 0

    changes.fastForEach { change ->
        if (change.pressed && change.previousPressed) {
            val position = if (useCurrent) change.position else change.previousPosition
            centroid += position
            centroidWeight++
        }
    }
    return if (centroidWeight == 0) {
        Offset.Unspecified
    } else {
        centroid / centroidWeight.toFloat()
    }
}

fun calculateRotation(changes:List<PointerInputChange>): Float {
    val pointerCount = changes.fastSumBy { if (it.previousPressed && it.pressed) 1 else 0 }
    if (pointerCount < 2) {
        return 0f
    }
    val currentCentroid = calculateCentroid(useCurrent = true, changes = changes)
    val previousCentroid = calculateCentroid(useCurrent = false, changes = changes)
    var rotation = 0f
    var rotationWeight = 0f

    // We want to weigh each pointer differently so that motions farther from the
    // centroid have more weight than pointers close to the centroid. Essentially,
    // a small distance change near the centroid could equate to a large angle
    // change and we don't want it to affect the rotation as much as pointers farther
    // from the centroid, which should be more stable.

    changes.fastForEach { change ->
        if (change.pressed && change.previousPressed) {
            val currentPosition = change.position
            val previousPosition = change.previousPosition
            val previousOffset = previousPosition - previousCentroid
            val currentOffset = currentPosition - currentCentroid

            val previousAngle = previousOffset.angle()
            val currentAngle = currentOffset.angle()
            val angleDiff = currentAngle - previousAngle
            val weight = (currentOffset + previousOffset).getDistance() / 2f

            // We weigh the rotation with the distance to the centroid. This gives
            // more weight to angle changes from pointers farther from the centroid than
            // those that are closer.
            rotation += when {
                angleDiff > 180f -> angleDiff - 360f
                angleDiff < -180f -> angleDiff + 360f
                else -> angleDiff
            } * weight

            // weight its contribution by the distance to the centroid
            rotationWeight += weight
        }
    }
    return if (rotationWeight == 0f) 0f else rotation / rotationWeight
}
