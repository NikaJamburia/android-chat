package ge.nika.chat.infrastructure.service

import ge.nika.chat.core.inbox.Inbox
import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageRepository
import ge.nika.chat.core.message.MessagingService
import java.time.LocalDateTime

class SimpleMessagingService(
    private val messageRepository: MessageRepository
): MessagingService {

    private val inboxSize: Int = 15
    private val inboxDays: Long = 60

    override fun viewInboxFor(user: String, onDate: LocalDateTime): Inbox =
        Inbox(user, inboxSize, onDate.minusDays(inboxDays), onDate,  messageRepository)


    override fun viewChat(forUser: String, withSender: String): List<Message> {
        TODO("Not yet implemented")
    }
}