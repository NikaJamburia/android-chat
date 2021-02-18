package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageStatus
import org.junit.Test
import java.time.LocalDateTime.parse
import kotlin.test.assertEquals

class TimeSinceMessageSentTest {

    @Test
    fun correctlyDeterminesTimeSinceSending() {
        val sendTime = parse("2021-02-14T17:51:00")
        mapOf(
            Pair("2021-02-14T17:52:00", "1 Minute Ago"),
            Pair("2021-02-14T17:53:00", "2 Minutes Ago"),
            Pair("2021-02-14T18:51:00", "1 Hour Ago"),
            Pair("2021-02-14T19:00:00", "1 Hour Ago"),
            Pair("2021-02-14T19:51:00", "2 Hours Ago"),
            Pair("2021-02-15T17:53:00", "1 Day Ago"),
            Pair("2021-02-16T17:53:00", "2 Days Ago")
        ).forEach {
            val viewTime = parse(it.key)
            val msg = Message("sender", "receiver", sendTime, "content", MessageStatus.NEW)
            val time = TimeSinceMessageSent(msg, viewTime).asString()
            assertEquals(it.value, time)
        }
    }
}