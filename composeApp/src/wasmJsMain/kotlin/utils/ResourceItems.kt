package utils

import models.SkillItem
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.MissingResourceException
import org.jetbrains.compose.resources.ResourceItem
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.wasm.unsafe.UnsafeWasmMemoryApi
import kotlin.wasm.unsafe.withScopedMemoryAllocator


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
    val btnRight = ResourceItem(setOf(), path = "drawable/btn_right.png", offset = -1, size = -1)


    val linkedIn = ResourceItem(setOf(), path = "drawable/linked_in.png", offset = -1, size = -1)
    val instagram = ResourceItem(setOf(), path = "drawable/instagram_img.png", offset = -1, size = -1)
    val x = ResourceItem(setOf(), path = "drawable/x_png.png", offset = -1, size = -1)
    val email = ResourceItem(setOf(), path = "drawable/email_img.png", offset = -1, size = -1)
    val brand = ResourceItem(setOf(), path = "drawable/brand_img.png", offset = -1, size = -1)



    // add more as you implement your resources
}

val devDescription = "Isaac is a seasoned mobile engineer with over 5years of experience. " +
        "He is very adaptive and  has experience with almost all mobile engineering stacks. " +
        "He has experience in Native Android, Native iOS, Flutter and KMM. Doubt it :) ?  Check out his experiences here"

val jobDescription = "Isaac is a responsible for all Mobile SDKs @ Telnyx, for Flutter, Android, and iOS. He occasionally works on native apps"


internal suspend fun loadResource(path: String): ByteArray {
    return loadImage(path).toByteArray()
}

suspend fun loadImage(url: String): ArrayBuffer {
    return suspendCoroutine { continuation ->
        val req = XMLHttpRequest()
        req.open("GET", url, true)
        req.responseType = "arraybuffer".toJsString().unsafeCast()

        req.onload = { _ ->
            val arrayBuffer = req.response
            if (arrayBuffer is ArrayBuffer) {
                continuation.resume(arrayBuffer)
            } else {
                continuation.resumeWithException(MissingResourceException(url))
            }
        }
        req.send("")
    }
}

internal fun jsInt8ArrayToKotlinByteArray(x: Int8Array): ByteArray {
    val size = x.length

    @OptIn(UnsafeWasmMemoryApi::class)
    return withScopedMemoryAllocator { allocator ->
        val memBuffer = allocator.allocate(size)
        val dstAddress = memBuffer.address.toInt()
        jsExportInt8ArrayToWasm(x, size, dstAddress)
        ByteArray(size) { i -> (memBuffer + i).loadByte() }
    }
}


internal external fun jsExportInt8ArrayToWasm(src: Int8Array, size: Int, dstAddr: Int)

fun ArrayBuffer.toByteArray(): ByteArray {
    val source = Int8Array(this, 0, byteLength)
    return jsInt8ArrayToKotlinByteArray(source)
}