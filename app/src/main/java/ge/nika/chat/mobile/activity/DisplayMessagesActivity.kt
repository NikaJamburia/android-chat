package ge.nika.chat.mobile.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.nika.chat.ApplicationContainer
import ge.nika.chat.R
import ge.nika.chat.mobile.view.MessagesRecyclerViewAdapter
import ge.nika.chat.core.inbox.InboxItem
import ge.nika.chat.util.MESSAGES_RECEIVER_NAME
import java.time.LocalDateTime

class DisplayMessagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_messages)
        val messagingService = (application as ApplicationContainer).messagingService

        val receiverName = intent.getStringExtra(MESSAGES_RECEIVER_NAME)!!
        displayReceiverName(receiverName)
        displayInbox(messagingService.viewInboxFor(receiverName, LocalDateTime.now()))

    }

    private fun displayInbox(inboxItems: List<InboxItem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.messagesView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            MessagesRecyclerViewAdapter(inboxItems)
    }

    private fun displayReceiverName(receiverName: String?) {
        val msgDisplay = findViewById<TextView>(R.id.receiverName)
        msgDisplay.text = "Messages for user $receiverName"
    }
}