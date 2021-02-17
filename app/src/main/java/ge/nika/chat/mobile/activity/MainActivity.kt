package ge.nika.chat.mobile.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import ge.nika.chat.R
import ge.nika.chat.util.MESSAGES_RECEIVER_NAME

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View) {
        val receiverName = findViewById<EditText>(R.id.recieverNameInput).text.toString()
        val intent = Intent(this, DisplayMessagesActivity::class.java)
            .apply { putExtra(MESSAGES_RECEIVER_NAME, receiverName) }
        startActivity(intent)
    }
}