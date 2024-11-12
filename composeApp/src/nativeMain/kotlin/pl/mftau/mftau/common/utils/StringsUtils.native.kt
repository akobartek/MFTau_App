package pl.mftau.mftau.common.utils

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.Foundation.decomposedStringWithCompatibilityMapping

actual fun String.normalizeMultiplatform(): String {
    val regex = Regex("[^A-Za-z0-9 ]")
    @Suppress("CAST_NEVER_SUCCEEDS")
    val str = this as NSString
    return str.decomposedStringWithCompatibilityMapping
        .replace("\n", " ")
        .replace(regex, "")
        .lowercase()
}

actual fun randomUUID(): String = NSUUID().UUIDString()

@OptIn(ExperimentalForeignApi::class)
fun fileDirectory(): String? {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory).path
}