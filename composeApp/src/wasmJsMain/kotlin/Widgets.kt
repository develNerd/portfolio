import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

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
        modifier = Modifier.size(100.dp),
        onDraw = {
            drawArc(brush = Brush.horizontalGradient(leftCornerBrush), startAngle = -90f, sweepAngle = 180f, useCenter = true)
        }
    )
}