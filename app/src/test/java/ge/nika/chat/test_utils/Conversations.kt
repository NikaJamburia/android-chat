package ge.nika.chat.test_utils

import ge.nika.chat.core.message.Message
import ge.nika.chat.core.message.MessageStatus
import java.time.LocalDateTime



fun nika_beqa_300Bucks() = listOf(
    Message(nika, beqa, LocalDateTime.parse("2021-01-19T12:31:00"), "Hi!", MessageStatus.SEEN),
    Message(beqa, nika, LocalDateTime.parse("2021-01-19T12:32:00"), "Hello!", MessageStatus.SEEN),
    Message(nika, beqa, LocalDateTime.parse("2021-01-19T12:33:00"), "How Are You?", MessageStatus.SEEN),
    Message(beqa, nika, LocalDateTime.parse("2021-01-19T12:35:00"), "Good! You?", MessageStatus.SEEN),
    Message(nika, beqa, LocalDateTime.parse("2021-01-19T12:37:00"), "Nice!", MessageStatus.NEW),
    Message(nika, beqa, LocalDateTime.parse("2021-01-19T12:38:00"), "Can I borrow 300 bucks?", MessageStatus.NEW)
)

fun nika_billy_Gachi() = listOf(
    Message(nika, billy, LocalDateTime.parse("2021-02-20T20:01:00"), "Oh shit, I'm sorry", MessageStatus.SEEN),
    Message(billy, nika, LocalDateTime.parse("2021-02-20T20:02:00"), "Sorry for what? Our daddy taught us not to be ashamed of our dicks, especially since they're such a good size and all", MessageStatus.SEEN),
    Message(nika, billy, LocalDateTime.parse("2021-02-20T20:03:00"), "Yeah, I see that. Your daddy gave you good advice.", MessageStatus.SEEN),
    Message(billy, nika, LocalDateTime.parse("2021-02-20T20:03:30"), "It gets bigger when I pull on it", MessageStatus.SEEN),
    Message(nika, billy, LocalDateTime.parse("2021-02-20T20:05:00"), "Hmmmm!", MessageStatus.SEEN),
    Message(billy, nika, LocalDateTime.parse("2021-02-20T20:06:00"), "Sometimes, I pull on it so hard, I rip the skin", MessageStatus.SEEN),
    Message(nika, billy, LocalDateTime.parse("2021-02-20T20:07:30"), "Well, my daddy taught me a few things too, like, uh, how not to rip the skin by using someone else's mouth, instead of your own hands", MessageStatus.SEEN),
    Message(billy, nika, LocalDateTime.parse("2021-02-20T20:07:45"), "Will you show me?", MessageStatus.SEEN),
    Message(nika, billy, LocalDateTime.parse("2021-02-20T20:08:10"), "I'd be right happy to.", MessageStatus.NEW)
)
