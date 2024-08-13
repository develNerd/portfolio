package utils

import models.SkillItem
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem


@OptIn(InternalResourceApi::class)
val skillItems = listOf(
    SkillItem("+5", ImageAsset.androidIcon),
    SkillItem("+2", ImageAsset.appleIcon),
    SkillItem("+2", ImageAsset.kotlinIcon),
    SkillItem("+3", ImageAsset.flutterIcon))



@OptIn(InternalResourceApi::class)
object ImageAsset {
     val profile = ResourceItem(setOf(), path = "drawable/my_profile.png", offset = -1, size = -1)

     val appleIcon = ResourceItem(setOf(), path = "drawable/ic_baseline_apple.png", offset = -1, size = -1)
     val androidIcon = ResourceItem(setOf(), path = "drawable/ic_android.png", offset = -1, size = -1)
     val kotlinIcon = ResourceItem(setOf(), path = "drawable/ic_baseline_apple.png", offset = -1, size = -1)
     val flutterIcon = ResourceItem(setOf(), path = "drawable/ic_flutter.png", offset = -1, size = -1)
     val arrowRight = ResourceItem(setOf(), path = "drawable/arrow_right_line.png", offset = -1, size = -1)

    // add more as you implement your resources
}

val devDescription = "Isaac is a seasoned mobile engineer with over 5years of experience. " +
        "He is very adaptive and  has experience with almost all mobile engineering stacks. " +
        "He has experience in Native Android, Native iOS, Flutter and KMM. Doubt it :) ?  Check out his experiences here"

val jobDescription = "Isaac is a responsible for all Mobile SDKs @ Telnyx, for Flutter, Android, and iOS. He occasionally works on native apps"