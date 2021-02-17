package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message
import java.time.LocalDateTime

class InboxItemFromMessageGroup(
    private val messages: List<Message>,
    private val opponent: String
) {
    fun create(): InboxItem {
        val latestMsg = messages.first()
        return InboxItem(
            opponent,
            MessageContentPreview(latestMsg, 50)
                .showTo(latestMsg.opponentFor(opponent)),
            TimeSinceMessageSent(
                latestMsg,
                LocalDateTime.now()
            ).asString(),
            isNotSeen(latestMsg),
            messages.count { isNotSeen(it) }
        )
    }

    private fun isNotSeen(msg: Message) =
        msg.sender == opponent && msg.isNew()
}