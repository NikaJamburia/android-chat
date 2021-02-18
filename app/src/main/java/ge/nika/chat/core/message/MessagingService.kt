package ge.nika.chat.core.message

import ge.nika.chat.core.inbox.Inbox
import java.time.LocalDateTime

interface MessagingService {
    fun viewInboxFor(user: String, onDate: LocalDateTime): Inbox
    fun viewChat(forUser: String, withSender: String): List<Message>
}