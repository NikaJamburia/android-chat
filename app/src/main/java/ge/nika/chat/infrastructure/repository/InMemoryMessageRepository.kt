package ge.nika.chat.infrastructure.repository

import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageRepository
import java.time.LocalDateTime

class InMemoryMessageRepository(
    private val existingMessages: MutableList<Message>
): MessageRepository {

    override fun send(message: Message) {
        existingMessages.add(message)
    }

    override fun listForUser(user: String, fromDate: LocalDateTime, toDate: LocalDateTime): List<Message> =
        existingMessages
            .filter { it.canBeViewedBy(user) }
            .filter { it.sendTime.isBefore(toDate) && it.sendTime.isAfter(fromDate) }
            .sortedByDescending { it.sendTime }

    override fun listForUser(user: String): List<Message> =
        existingMessages
            .filter { it.canBeViewedBy(user) }
            .sortedByDescending { it.sendTime }

}