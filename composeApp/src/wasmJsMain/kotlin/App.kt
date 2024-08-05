import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.SkillItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.painterResource

import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.compose_multiplatform
import utils.ImageAsset
import utils.devDescription

@OptIn(InternalResourceApi::class)
@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }

        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomePage()
            Divider(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp))


            Button(onClick = { showContent = !showContent }) {
                Text("Click !")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }

}



@OptIn(InternalResourceApi::class)
private val resourceItem = ResourceItem(setOf(), path = "drawable/my_profile.png", offset = -1, size = -1)

@OptIn(InternalResourceApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomePage() {
    Row(modifier = Modifier.fillMaxWidth()) {
        print("Height" + LocalWindowInfo.current.containerSize.height)

        Column(
            modifier = Modifier.weight(0.5F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CircleBrush()
            Image(painterResource(DrawableResource(id = "", items = setOf(ImageAsset.profile))), null)
            Spacer(modifier = Modifier.size(40.dp))
            DevName()
        }

        Column(modifier = Modifier.weight(0.5F), verticalArrangement = Arrangement.spacedBy(15.dp)) {
            TitleComponent(title = "Mobile Engineer @ Telnyx (SDK & Apps)", linkText = "See Skills",null)
            TitleComponent(title = "Website Powered by ", linkText = "View Code","Kotlin Wasm")
            Text(text = devDescription, style = TextStyle(fontSize = 24.sp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                LeftCornerArc()
            }
        }

    }

}




@OptIn(InternalResourceApi::class)
@Composable
fun TitleHeader(title: String){
    Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
        ToolIndicator()
        Text(text = title, style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold), textAlign = TextAlign.Center)
        Image(painterResource(DrawableResource(id = "", items = setOf(resourceItem))), null)
    }
}






@Composable
fun TitleComponent(title: String,linkText:String, subTitle: String?) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ToolIndicator()
        Spacer(Modifier.size(5.dp))
        Text(
            text = title, style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium
            )
        )
        if (subTitle != null) {
            Text(
                text = subTitle, style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        LinkText(text = linkText)

    }
}

@OptIn(InternalResourceApi::class)
@Composable
fun LinkText(text: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = buildAnnotatedString {
            pushStringAnnotation(text, "https://developer.android.com/develop/ui/compose/text/style-text")
        })
        Image(painterResource(DrawableResource(id = "", items = setOf(ImageAsset.arrowRight))), null)
    }

}

val brush = (listOf(Color(0xFFDA6B75), Color(0xFFDC6C6F), Color(0xFFF2831E)))
val brushText = (listOf(Color(0xFFDA6B75), Color(0xFFDC6C6F), Color(0xFF5967C4)))

val leftCornerBrush = (listOf(Color(0xFF6676E0), Color(0xFF38407A)))


@Composable
fun DevName() {
    Text(
        text = "Isaac Akakpo Koku",
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            brush = Brush.linearGradient(
                colors = brushText
            )
        )
    )

}



