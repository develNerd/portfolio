package models

import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

@OptIn(InternalResourceApi::class)
data class SkillItem constructor(val experience:String, val icon:ResourceItem)