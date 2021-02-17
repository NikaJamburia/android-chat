package ge.nika.chat.infrastructure.service

import ge.nika.chat.core.inbox.InboxItem
import ge.nika.chat.core.inbox.InboxItemFromMessageGroup
import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageRepository
import ge.nika.chat.core.message.MessagingService
import java.time.LocalDateTime

class SimpleMessagingService(
    private val messageRepository: MessageRepository
): MessagingService {

    private val maximumInboxElements: Int = 15

    override fun viewInboxFor(user: String, onDate: LocalDateTime): List<InboxItem> =
        messageRepository.listForUser(user)
            .asSequence()
            .groupBy { it.opponentFor(user) }
            .map { InboxItemFromMessageGroup(
                it.value,
                it.key
            ).create() }
            .take(maximumInboxElements)


    override fun viewChat(forUser: String, withSender: String): List<Message> {
        TODO("Not yet implemented")
    }
}