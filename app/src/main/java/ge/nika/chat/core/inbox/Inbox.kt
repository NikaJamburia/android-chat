package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.MessageRepository
import java.time.LocalDateTime

class Inbox (
    private val user: String,
    private val size: Int,
    private val fromDate: LocalDateTime,
    private val viewingDate: LocalDateTime,
    private val messageRepository: MessageRepository
) {
    fun asList(): List<InboxItem> =
        messageRepository.listForUser(user, fromDate, viewingDate)
            .sortedByDescending { it.sendTime }
            .groupBy { it.opponentFor(user) }
            .asSequence()
            .map { InboxItemFromMessageGroup(it.value, user, viewingDate).create() }
            .take(size)
            .toList()
}