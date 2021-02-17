package ge.nika.chat.core.message

import ge.nika.chat.core.inbox.InboxItem
import java.time.LocalDateTime

interface MessagingService {
    fun viewInboxFor(user: String, onDate: LocalDateTime): List<InboxItem>
    fun viewChat(forUser: String, withSender: String): List<Message>
}