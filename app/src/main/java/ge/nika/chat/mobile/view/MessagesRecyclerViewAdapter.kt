package ge.nika.chat.mobile.view

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.nika.chat.R
import ge.nika.chat.core.inbox.InboxItem

class MessagesRecyclerViewAdapter(
    private val inboxItem: List<InboxItem>
): RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.messages_list_row, parent, false))

    override fun getItemCount(): Int = inboxItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = inboxItem[position]
        if (item.containsNewMessages) {
            holder.markUnseen(item.newMessagesCount)
        }

        holder.msgContent.text = item.lastMessageContent
        holder.msgTimeAgo.text = item.timeAfterLastMsg
        holder.msgSender.text = item.sender

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