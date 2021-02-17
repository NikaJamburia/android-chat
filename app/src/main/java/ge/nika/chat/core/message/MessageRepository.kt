package ge.nika.chat.core.message

import java.time.LocalDateTime

interface MessageRepository {
    fun send(message: Message)
    fun listForUser(user: String, fromDate: LocalDateTime, toDate: LocalDateTime): List<Message>
    fun listForUser(user: String): List<Message>
}