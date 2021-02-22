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
        return sequenceOf(
            Pair(duration.toDays(), "Day"),
            Pair(duration.toHours(), "Hour"),
            Pair(duration.toMinutes(), "Minute"),
            Pair(duration.seconds, "Second")
        )
            .filter { it.first != 0L }
            .map { ago(it.first, it.second) }
            .first()
    }

    private fun ago(number: Long, timeUnit: String): String =
            "$number ${timeUnit}${ if (number>1) {"s"} else {""} } Ago"
}