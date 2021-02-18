package ge.nika.chat.core.inbox

data class InboxItem (
    val opponentName: String,
    val lastMessageContent: String,
    val timeSinceLastMsg: String,
    val newMessagesCount: Int
)