package ge.nika.chat.core.inbox

data class InboxItem (
    val sender: String,
    val lastMessageContent: String,
    val timeAfterLastMsg: String,
    val containsNewMessages: Boolean,
    val newMessagesCount: Int
)