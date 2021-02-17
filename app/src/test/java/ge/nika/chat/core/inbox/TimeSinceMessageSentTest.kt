package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageStatus
import org.junit.Test
import java.time.LocalDateTime.parse
import kotlin.test.assertEquals

class TimeSinceMessageSentTest {

    @Test
    fun aaa() {
        val sendTime = parse("2021-02-14T17:51:00")
        val msg = Message("sender", "receiver", sendTime, "content", MessageStatus.NEW)
        val viewingTime = parse("2021-02-14T17:52:00")

        val time = TimeSinceMessageSent(msg, viewingTime).asString()
        assertEquals("1 Minute Ago", time)
    }
}