import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import models.SkillItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.compose_multiplatform
import sh.calvin.autolinktext.AutoLinkText
import utils.ImageAsset
import utils.devDescription
import utils.jobDescription

//

val subTextColor = Color(0xFF718096)

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
@Preview
fun HomePage() {
    Column {
        Row {
            Box(modifier = Modifier.weight(0.5F), contentAlignment = Alignment.Center) {
                CircleBrush()
            }
            Spacer(modifier = Modifier.weight(0.5F))
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {



            print("Width" + LocalWindowInfo.current.containerSize.width)

            Column(
                modifier = Modifier.weight(0.5F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painterResource(DrawableResource(id = "", items = setOf(ImageAsset.profile))), null)
                Spacer(modifier = Modifier.size(60.dp))
                DevName()
            }

            Column(modifier = Modifier.weight(0.5F), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Spacer(modifier = Modifier.size(40.dp))
                TitleComponent(title = "Mobile Engineer @ Telnyx (SDK & Apps)", linkText = "See Skills",null)
                Text(text = devDescription, style = TextStyle(fontSize = 20.sp, color = subTextColor), modifier = Modifier.padding(end = 200.dp))
                TitleComponent(title = "Website Powered by ", linkText = "View Code","Kotlin Wasm")
                Text(text = jobDescription, style = TextStyle(fontSize = 20.sp, color = subTextColor), modifier = Modifier.padding(end = 200.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                    LeftCornerArc()
                }
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
                    fontWeight = FontWeight.Bold
                )
            )
        }
        LinkText(text = linkText)

    }
}

@OptIn(InternalResourceApi::class, ExperimentalTextApi::class)
@Composable
fun LinkText(text: String) {
    val uriHandler = LocalUriHandler.current

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
        ClickableText(text = buildAnnotatedString {
            append(AnnotatedString(text = text))

        }, onClick = {
            uriHandler.openUri("https://kotlinlang.org/docs/multiplatform.html")
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



