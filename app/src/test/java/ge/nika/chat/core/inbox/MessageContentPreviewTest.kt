package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageStatus
import org.junit.Test
import java.lang.IllegalStateException
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MessageContentPreviewTest {

    @Test
    fun correctlyShowsOpponentNameInPreview() {
        val sender = "Nika"
        val receiver = "Beqa"
        val stranger = "vigaca"
        val msg = Message(sender, receiver, LocalDateTime.now(), "Hello", MessageStatus.NEW)

        val preview = MessageContentPreview(msg, 10)
        val previewForSender = preview.showTo(sender)
        val previewForReceiver = preview.showTo(receiver)

        assertEquals("You: Hello", previewForSender)
        assertEquals("Nika: Hello", previewForReceiver)
        assertFailsWith<IllegalStateException>("User vigaca cant view this message") { preview.showTo(stranger) }
    }

    @Test
    fun displaysCorrectNumberOfSymbolsInPreview() {
        val msg = Message("Nika", "Beqa", LocalDateTime.now(), "Hello! How are you?", MessageStatus.NEW)

        var preview = MessageContentPreview(msg, 10).showTo("Nika")
        assertEquals("You: Hello! How...", preview)
        assertEquals(10, preview.removePrefix("You: ").removeSuffix("...").length)

        preview = MessageContentPreview(msg, 7).showTo("Nika")
        assertEquals("You: Hello! ...", preview)
        assertEquals(7, preview.removePrefix("You: ").removeSuffix("...").length)

        preview = MessageContentPreview(msg, 20).showTo("Nika")
        assertEquals("You: Hello! How are you?", preview)
    }
}