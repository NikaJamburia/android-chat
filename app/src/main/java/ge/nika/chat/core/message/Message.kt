package ge.nika.chat.core.message

import java.time.LocalDateTime

data class Message (
    val sender: String,
    val receiver: String,
    val sendTime: LocalDateTime,
    val content: String,
    val status: MessageStatus
) {
    fun isNew() = status == MessageStatus.NEW

    fun canBeViewedBy(user: String): Boolean = user == sender || user == receiver

    fun opponentFor(user: String): String {
        if (!canBeViewedBy(user)) { error("User $user is not part of this conversation") }
        return if (sender == user) {
            receiver
        } else {
            sender
        }
    }

    fun markSeen(): Message =
        Message(sender, receiver, sendTime, content, MessageStatus.SEEN)

}


enum class MessageStatus {
    NEW, SEEN
}