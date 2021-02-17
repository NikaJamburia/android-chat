package ge.nika.chat.core.inbox

import ge.nika.chat.core.message.Message

class MessageContentPreview(
    private val message: Message,
    private val previewSize: Int
) {
    fun showTo(user: String): String {
        val text = message.content
        return if (text.length > previewSize) {
            prefixFor(user) + text.substring(0, previewSize) + "..."
        } else {
            prefixFor(user) + text
        }
    }

    private fun prefixFor(user: String): String =
        if (message.sender == user) {
            "You: "
        } else {
            "${message.sender}: "
        }

}