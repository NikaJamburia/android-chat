package ge.nika.chat

import android.app.Application
import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageStatus
import ge.nika.chat.infrastructure.repository.InMemoryMessageRepository
import ge.nika.chat.core.message.MessageRepository
import ge.nika.chat.infrastructure.service.SimpleMessagingService
import ge.nika.chat.core.message.MessagingService
import java.time.LocalDateTime

class ApplicationContainer : Application() {
    val messageRepository: MessageRepository =
        InMemoryMessageRepository(
            mutableListOf(
                Message("Beqa", "Nika", LocalDateTime.parse("2021-02-13T12:30:00"), "Hello", MessageStatus.SEEN),
                Message("Beqa", "Nika", LocalDateTime.parse("2021-02-13T12:31:00"), "How are you?", MessageStatus.NEW),
                Message("Nika", "Luka", LocalDateTime.parse("2021-02-13T12:25:00"), "she yleo", MessageStatus.NEW),
                Message("Luka", "Nika", LocalDateTime.parse("2021-02-13T19:28:00"), "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", MessageStatus.NEW),
                Message("Nika", "Bika", LocalDateTime.parse("2021-02-13T12:31:00"), "Privet", MessageStatus.SEEN),
                Message("Bika", "Nika", LocalDateTime.parse("2021-02-13T12:32:00"), "Gaumarjos", MessageStatus.SEEN),
                Message("Nika", "Bika", LocalDateTime.parse("2021-02-13T12:33:00"), "Rogor xar??", MessageStatus.SEEN),
                Message("Bika", "Nika", LocalDateTime.parse("2021-02-13T12:34:00"), "cudad", MessageStatus.SEEN),
                Message("Nika", "Bika", LocalDateTime.parse("2021-02-13T12:34:00"), ":(", MessageStatus.NEW),
                Message("Nika", "Bika", LocalDateTime.parse("2021-02-13T12:34:30"), "cudia", MessageStatus.NEW),
                Message("spam", "Nika", LocalDateTime.parse("2021-02-13T12:34:30"), "sapnu puas", MessageStatus.NEW),
                Message("spam", "Nika", LocalDateTime.parse("2021-02-13T12:34:30"), "sapnu puas", MessageStatus.NEW),
                Message("spam", "Nika", LocalDateTime.parse("2021-02-13T12:34:30"), "sapnu puas", MessageStatus.NEW),
                Message("spam", "Nika", LocalDateTime.parse("2021-02-13T12:34:30"), "sapnu puas", MessageStatus.NEW),
                Message("spam", "Nika", LocalDateTime.parse("2021-02-13T12:34:30"), "sapnu puas", MessageStatus.NEW),
                Message("spam", "Nika", LocalDateTime.parse("2021-02-13T12:34:30"), "sapnu puas", MessageStatus.NEW)
            )
        )

    val messagingService: MessagingService =
        SimpleMessagingService(
            messageRepository
        )
}