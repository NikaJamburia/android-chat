package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import java.time.Duration
import java.time.LocalDateTime

class TimeSinceMessageSent(
    private val message: Message,
    private val fromTime: LocalDateTime
) {
    fun asString(): String {
        val duration = Duration.between(message.sendTime, fromTime)
        return if (duration.toDays() < 1) {
            if (duration.toHours() < 1) {
                ago(duration.toMinutes(), "Minute")
            } else {
                ago(duration.toHours(), "Hour")
            }
        } else {
            ago(duration.toDays(), "Day")
        }
    }

    private fun ago(number: Long, timeUnit: String): String =
            "$number ${timeUnit}${ if (number>1) {"s"} else {""} } Ago"
}