package pl.mftau.mftau.common.utils

import java.text.Normalizer
import java.util.UUID

actual fun String.normalizeMultiplatform(): String {
    val regex = Regex("[^A-Za-z0-9 ]")
    return Normalizer.normalize(this, Normalizer.Form.NFKD)
        .replace("\n", " ")
        .replace(regex, "")
        .lowercase()
}

actual fun randomUUID(): String = UUID.randomUUID().toString()