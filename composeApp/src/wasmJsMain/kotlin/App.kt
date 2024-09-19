import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.SkillItem
import org.jetbrains.compose.resources.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.khronos.webgl.ArrayBuffer
import org.w3c.xhr.XMLHttpRequest

import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.compose_multiplatform
import sh.calvin.autolinktext.AutoLinkText
import utils.ImageAsset
import utils.devDescription
import utils.jobDescription
import utils.loadResource
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//

val subTextColor = Color(0xFF718096)

var Inter: FontFamily? = null // init in platform code

suspend fun loadFont() {
    val italic = loadResource("inter_italic.ttf")

    Inter = FontFamily(
        Font(identity = "InterItalic", data = italic, weight = FontWeight.Normal),
    )
}


@OptIn(InternalResourceApi::class, DelicateCoroutinesApi::class)
@Composable
fun App() {


    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }

        var currentProject by remember {
            mutableStateOf(-1)
        }




        Column(
            Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomePage()
            //Divider(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp))

            Spacer(modifier = Modifier.size(40.dp))

            MajorTitleComponent(title = "Major Projects ", linkText = "", subTitle = null)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Projects() {
                    currentProject = -1
                    GlobalScope.launch {
                        delay(10)
                        currentProject = it
                    }
                }

                AnimatedVisibility(visible = currentProject != -1){
                    if (currentProject != -1){
                        ProjectDialog(projects[currentProject]) {
                            currentProject = -1
                        }
                    }

                }
            }
            Footer()
        }
    }

}

@OptIn(InternalResourceApi::class)
@Composable
fun Footer() {
    Card {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                // Social Media Links
                Column(modifier = Modifier.weight(1f).padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(30.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Image(painterResource(DrawableResource(id = "", items = setOf(ImageAsset.brand))), null)
                        Text(text = "Learn from yesterday, live for today, hope for tomorrow by Einstein", fontFamily = Inter, fontStyle = FontStyle.Italic, fontSize = 14.sp)
                    }
                    Text(text = "You can contact me on social media using the following links", fontSize = 18.sp)
                    Row(horizontalArrangement = Arrangement.spacedBy(100.dp)) {
                        SocialMediaLink("LinkedIn", ImageAsset.linkedIn, "https://www.linkedin.com/in/isaac-akakpo/")
                        SocialMediaLink("Instagram", ImageAsset.instagram, "https://www.instagram.com/isaac_akakpo/")
                        SocialMediaLink("X", ImageAsset.x, "https://x.com/edem_aik")
                        SocialMediaLink("isaacakakpo4@gmail.com", ImageAsset.instagram, "mailto:isaacakakpo4@gmail.com")
                    }

                }
            }

            Box(modifier = Modifier.fillMaxWidth().background(brush = Brush.horizontalGradient(brushText)).padding(vertical = 20.dp), contentAlignment = Alignment.Center) {
                Text(text = "© 2024 Isaac Akakpo Koku. All rights reserved", color = Color.White)
            }
        }

    }

}

@OptIn(InternalResourceApi::class)
@Composable
fun SocialMediaLink(text: String, image: ResourceItem, url: String) {
    val uriHandler = LocalUriHandler.current
    Row(modifier = Modifier.clickable {
        uriHandler.openUri(url)
    }, verticalAlignment = Alignment.CenterVertically) {
        Image(painterResource(DrawableResource(id = "", items = setOf(image))), null)
        Text(text = text, fontFamily = FontFamily.Cursive, fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
    }
}

@OptIn(InternalResourceApi::class)
@Composable
fun ProjectDialog(project: Project, onDismissRequest: () -> Unit) {
    Box (modifier = Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            val image by remember {
                mutableStateOf(project.image!!)
            }

            Image(painterResource(DrawableResource(id = "", items = setOf(ImageAsset.btnRight))), null, modifier = Modifier.clickable {
                onDismissRequest()
            })

            Spacer(modifier = Modifier.size(20.dp))

            Box(modifier = Modifier.height(500.dp).weight(0.35f).background(brush = Brush.linearGradient(brushText)), contentAlignment = Alignment.Center) {
                Image( painter =  painterResource(project.drawableResource!!), null, modifier = Modifier.size(60.dp))
            }
            Box(modifier = Modifier.weight(0.65f).padding(horizontal = 8.dp)) {
                Column(modifier = Modifier.padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    DialogTIle(project.title)
                    Text(project.description, fontSize = 18.sp, color = subTextColor, textAlign = TextAlign.Center)
                    LinkText("View Project", project.link)
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
                TitleComponent(title = "This Website is Powered and developed using ", linkText = "View Code","Kotlin Wasm (Jetpack Compose)", url = "https://github.com/develNerd/portfolio")
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
fun TitleComponent(title: String,linkText:String, subTitle: String?,url: String? = null) {
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
        LinkText(text = linkText, url = url)

    }
}

@Composable
fun MajorTitleComponent(title: String,linkText:String, subTitle: String?) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = title, style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
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

    }
}

@OptIn(InternalResourceApi::class, ExperimentalTextApi::class)
@Composable
fun LinkText(text: String,url:String? = "") {
    val uriHandler = LocalUriHandler.current

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
        ClickableText(text = buildAnnotatedString {
            append(AnnotatedString(text = text))

        }, onClick = {
            uriHandler.openUri(url ?: "https://kotlinlang.org/docs/multiplatform.html")
        })
        Image(painterResource(DrawableResource(id = "", items = setOf(ImageAsset.arrowRight))), null)
    }

}

val brush = (listOf(Color(0xFFDA6B75), Color(0xFFDC6C6F), Color(0xFFF2831E)))
val brushText = (listOf(Color(0xFFDA6B75), Color(0xFFDC6C6F), Color(0xFF5967C4)))

val leftCornerBrush = (listOf(Color(0xFF6676E0), Color(0xFF38407A)))
val dialogBackground = (listOf(Color(0xFF53F9E5), Color(0xFF5CEDA8), Color(0xFF44D28E)))

@Composable
fun DialogTIle(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            brush = Brush.linearGradient(
                colors = brushText
            )
        )
    )

}



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
