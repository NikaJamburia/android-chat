package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import java.time.LocalDateTime

class InboxItemFromMessageGroup(
    private val messages: List<Message>,
    private val user: String,
    private val onTime: LocalDateTime
) {
    fun create(): InboxItem {
        val latestMsg = messages.last()
        return InboxItem(
            latestMsg.opponentFor(user),
            MessageContentPreview(latestMsg, 50).showTo(user),
            TimeSinceMessageSent(latestMsg, onTime).asString(),
            messages.count { it.isNotSeenBy(user) }
        )
    }

    private fun Message.isNotSeenBy(user: String) =
        this.sender == this.opponentFor(user) && this.isNew()
}