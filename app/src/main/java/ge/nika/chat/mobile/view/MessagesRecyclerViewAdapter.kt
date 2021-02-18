package ge.nika.chat.mobile.view

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.nika.chat.R
import ge.nika.chat.core.inbox.Inbox
import ge.nika.chat.core.inbox.InboxItem

class MessagesRecyclerViewAdapter(
    inbox: Inbox
): RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> () {

    private val inboxItems = inbox.asList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.messages_list_row, parent, false))

    override fun getItemCount(): Int = inboxItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = inboxItems[position]
        if (item.newMessagesCount > 0) {
            holder.markUnseen(item.newMessagesCount)
        }

        holder.msgContent.text = item.lastMessageContent
        holder.msgTimeAgo.text = item.timeSinceLastMsg
        holder.msgSender.text = item.opponentName

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var msgSender: TextView = view.findViewById(R.id.msgSender) as TextView
        var msgContent: TextView = view.findViewById(R.id.msgContent) as TextView
        var msgTimeAgo: TextView = view.findViewById(R.id.msgTimeAgo) as TextView
        var unseenItemCount: TextView = view.findViewById(R.id.unseenItemCount) as TextView

        fun markUnseen(unseenMsgCount: Int) {
            msgTimeAgo.markUnseen()
            msgSender.markUnseen()
            unseenItemCount.text = unseenMsgCount.toString()
            unseenItemCount.visibility = View.VISIBLE
        }

        private fun TextView.markUnseen() {
            this.setTypeface(this.typeface, Typeface.BOLD)
            this.setTextColor(Color.BLACK)
        }
    }
}