package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.MessageRepository
import ge.nika.chat.infrastructure.repository.InMemoryMessageRepository
import ge.nika.chat.test_utils.*
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.time.LocalDateTime
import java.time.LocalDateTime.parse
import kotlin.test.assertEquals

class InboxTest {

    private val fromTime: LocalDateTime = parse("2020-02-20T17:00:00")
    private var viewingTime: LocalDateTime = parse("2021-02-20T20:10:00")

    private val existingConversations = listOf(nika_beqa_300Bucks(), nika_billy_Gachi()).flatten().toMutableList()
    private val messageRepository: MessageRepository = InMemoryMessageRepository(existingConversations)

    @Test
    fun shouldGenerateInboxesWithAllParticipants() {

        val nikasInbox = Inbox(nika, 5, fromTime, viewingTime, messageRepository).asList()

        assertEquals(2, nikasInbox.size)
        assertEquals("You: I'd be right happy to.", nikasInbox.first().lastMessageContent)
        assertEquals("1 Minute Ago", nikasInbox.first().timeSinceLastMsg)
        assertEquals(0, nikasInbox.first().newMessagesCount)
        assertEquals(billy, nikasInbox.first().opponentName)

        assertEquals("You: Can I borrow 300 bucks?", nikasInbox.last().lastMessageContent)
        assertEquals("32 Days Ago", nikasInbox.last().timeSinceLastMsg)
        assertEquals(0, nikasInbox.last().newMessagesCount)
        assertEquals(beqa, nikasInbox.last().opponentName)

        val billysInbox = Inbox(billy, 5, fromTime, viewingTime, messageRepository).asList()
        assertEquals(1, billysInbox.size)
        assertEquals("Nika: I'd be right happy to.", billysInbox.first().lastMessageContent)
        assertEquals("1 Minute Ago", billysInbox.first().timeSinceLastMsg)
        assertEquals(1, billysInbox.first().newMessagesCount)
        assertEquals(nika, billysInbox.first().opponentName)

    }

    @Test
    fun inboxShouldNotIncludeMessagesThatDontFitInTime() {
        viewingTime = parse("2021-02-20T20:07:31")

        val billysInbox = Inbox(billy, 5, fromTime, viewingTime, messageRepository).asList()
        assertEquals(1, billysInbox.size)
        assertEquals("Nika: Well, my daddy taught me a few things too, like, u...", billysInbox.first().lastMessageContent)
        assertEquals("1 Second Ago", billysInbox.first().timeSinceLastMsg)
        assertEquals(0, billysInbox.first().newMessagesCount)
        assertEquals(nika, billysInbox.first().opponentName)
    }

}