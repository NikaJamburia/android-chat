package ge.nika.chat.core.inbox

import ge.nika.chat.test_utils.beqa
import ge.nika.chat.test_utils.nika
import ge.nika.chat.test_utils.nika_beqa_300Bucks
import org.junit.Test
import java.lang.IllegalStateException
import java.time.LocalDateTime.parse
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InboxItemFromMessageGroupTest {

    @Test
    fun correctInformationIsShownToUsers() {

        val inboxViewTime = parse("2021-01-19T12:58:00")

        val messages = nika_beqa_300Bucks()

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