package ge.nika.chat.core.message

import ge.nika.chat.core.message.MessageStatus.NEW
import ge.nika.chat.core.message.MessageStatus.SEEN
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException
import java.time.LocalDateTime.now
import kotlin.test.assertFailsWith

class MessageTest {

    @Test
    fun knowsIfItIsNew() {
        val seenMsg = Message("sender", "receiver", now(), "Hi", SEEN)
        assertFalse(seenMsg.isNew())

        val newMsg = Message("sender", "receiver", now(), "Hi", NEW)
        assertTrue(newMsg.isNew())
    }

    @Test
    fun letsOnlySenderAndReceiverToViewItself() {
        val sender = "sender"
        val receiver = "receiver"
        val stranger = "stranger"
        val msg = Message(sender, receiver, now(), "Hi", SEEN)

        assertTrue(msg.canBeViewedBy(sender))
        assertTrue(msg.canBeViewedBy(receiver))
        assertFalse(msg.canBeViewedBy(stranger))
    }

    @Test
    fun correctlyIdentifiesConversationOpponentForUser() {
        val nika = "nika"
        val gio = "gio"
        val stranger = "stranger"
        val msg = Message(nika, gio, now(), "Hi", SEEN)

        assertEquals(gio, msg.opponentFor(nika))
        assertEquals(nika, msg.opponentFor(gio))
        assertEquals(gio, msg.opponentFor(msg.opponentFor(gio)))

        assertFailsWith<IllegalStateException>("User $stranger is not part of this conversation")
        { msg.opponentFor(stranger) }

    }

    @Test
    fun shouldCorrectlyBeMarkedSeen() {
        val newMsg = Message("sender", "receiver", now(), "Hi", NEW)
        val seen = newMsg.markSeen()
        assertFalse(seen.isNew())
    }
}