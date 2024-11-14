package pl.mftau.mftau.common.utils

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.getFormattedDateTime() = format(
    LocalDateTime.Format {
        time(
            LocalTime.Format {
                hour()
                char(':')
                minute()
                char(':')
                second()
            }
        )
        chars(" - ")
        date(
            LocalDate.Format {
                dayOfMonth()
                char('.')
                monthNumber()
                char('.')
                year()
            }
        )
    }
)

fun LocalDateTime.getFormattedDate() = format(
    LocalDateTime.Format {
        date(
            LocalDate.Format {
                dayOfMonth()
                char('.')
                monthNumber()
                char('.')
                year()
            }
        )
    }
)

fun Long.getFormattedDate() =
    Instant.fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .getFormattedDate()

fun Timestamp.getFormattedDate() = (nanoseconds / 100L).getFormattedDate()