import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.SkillItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource
import utils.ImageAsset
import utils.skillItems

data class ExperienceJourneyItem(val title: String, val description: String)

val firstExperienceJourneyItems = listOf(
    ExperienceJourneyItem(
        "Computer Sc. and Eng @ University of Mines and Tch.",
        "We handle all aspects of vetting and choosing the right team that you don't have the time, expertise, or desire to do."
    ),
    ExperienceJourneyItem(
        "Android Engineer @ Farmerline LTD",
        "We handle all aspects of vetting and choosing the right team that you don't have the time, expertise, or desire to do."
    ),
    ExperienceJourneyItem(
        "Mobile SDK Engineer @ Telnyx LLC",
        "We handle all aspects of vetting and choosing the right team that you don't have the time, expertise, or desire to do."
    )

)

val secondExperienceJourneyItems = listOf(
    ExperienceJourneyItem(
        "Computer Sc. and Eng @ University of Mines and Tch.",
        "We handle all aspects of vetting and choosing the right team that you don't have the time, expertise, or desire to do."
    ),
    ExperienceJourneyItem(
        "Android Engineer @ Farmerline LTD",
        "We handle all aspects of vetting and choosing the right team that you don't have the time, expertise, or desire to do."
    ),
    ExperienceJourneyItem(
        "Mobile SDK Engineer @ Telnyx LLC",
        "We handle all aspects of vetting and choosing the right team that you don't have the time, expertise, or desire to do."
    )

)

@Composable
fun SkillAndExperienceSection() {
    Box {

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleHeader("Skills and Experience")
            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                skillItems.forEach {
                    SkillItemView(it)
                }
            }

            //Experience Journey
            Text("Experience Journey", style = TextStyle(fontSize = 24.sp, color = Color.Black))



        }


    }
}

@Composable
fun ExperienceJourney() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

    }
}


@Composable
fun ExperienceJourneyItemView(experienceJourneyItem: ExperienceJourneyItem) {
    Box(
        modifier = Modifier.border(2.dp, brush = Brush.horizontalGradient(brush), shape = RoundedCornerShape(5.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                experienceJourneyItem.title,
                style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                experienceJourneyItem.description,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Center,
                maxLines = 3
            )
        }

    }
}

@OptIn(InternalResourceApi::class)
@Composable
fun SkillItemView(skillItem: SkillItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SkillCircleBrush(skillItem.experience)
        Divider(modifier = Modifier.height(100.dp).width(1.dp))
        Image(painterResource(DrawableResource(id = "", items = setOf(skillItem.icon))), null)

    }

}


@Composable
private fun SkillCircleBrush(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
        contentAlignment = Alignment.Center
    ) {
        val textMeasurer = rememberTextMeasurer()

        val style = TextStyle(
            fontSize = 50.sp,
            color = Color.Black,
            background = Color.Red.copy(alpha = 0.2f)
        )

        val textLayoutResult = remember(text) {
            textMeasurer.measure(text, style)
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                center = Offset(
                    x = center.x,
                    y = center.y
                ),
                radius = 350f,
                color = Color.Blue,
                style = Stroke(
                    width = 8f
                )
            )
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = style,
                topLeft = Offset(
                    x = center.x - textLayoutResult.size.width / 2,
                    y = center.y - textLayoutResult.size.height / 2,
                )
            )


        }
    }
}

