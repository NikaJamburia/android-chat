package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageStatus.*
import org.junit.Test
import java.lang.IllegalStateException
import java.time.LocalDateTime.parse
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InboxItemFromMessageGroupTest {

    @Test
    fun correctInformationIsShownToUsers() {
        val nika = "Nika"
        val beqa = "Beqa"
        val inboxViewTime = parse("2021-01-19T12:58:00")

        val messages = listOf(
            Message(nika, beqa, parse("2021-01-19T12:31:00"), "Hi!", SEEN),
            Message(beqa, nika, parse("2021-01-19T12:32:00"), "Hello!", SEEN),
            Message(nika, beqa, parse("2021-01-19T12:33:00"), "How Are You?", SEEN),
            Message(beqa, nika, parse("2021-01-19T12:35:00"), "Good! You?", SEEN),
            Message(nika, beqa, parse("2021-01-19T12:37:00"), "Nice!", NEW),
            Message(nika, beqa, parse("2021-01-19T12:38:00"), "Can I borrow 300 bucks?", NEW)
        )

        val itemForNika = InboxItemFromMessageGroup(messages, nika, inboxViewTime).create()
        assertEquals(beqa, itemForNika.opponentName)
        assertEquals("You: Can I borrow 300 bucks?", itemForNika.lastMessageContent)
        assertEquals("20 Minutes Ago", itemForNika.timeSinceLastMsg)
        assertEquals(0, itemForNika.newMessagesCount)

        val itemForBeqa = InboxItemFromMessageGroup(messages, beqa, inboxViewTime).create()
        assertEquals(nika, itemForBeqa.opponentName)
        assertEquals("Nika: Can I borrow 300 bucks?", itemForBeqa.lastMessageContent)
        assertEquals("20 Minutes Ago", itemForBeqa.timeSinceLastMsg)
        assertEquals(2, itemForBeqa.newMessagesCount)

        assertFailsWith<IllegalStateException> ("User stranger is not part of this conversation")
        { InboxItemFromMessageGroup(messages, "stranger", inboxViewTime).create() }
    }
}