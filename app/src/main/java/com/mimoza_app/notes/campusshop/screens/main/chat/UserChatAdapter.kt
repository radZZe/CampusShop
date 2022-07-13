package com.mimoza_app.notes.campusshop.screens.main.chat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.ChatMessage
import com.mimoza_app.notes.campusshop.util.PreferenceManager
import com.mimoza_app.notes.campusshop.util.RECEIVED
import com.mimoza_app.notes.campusshop.util.SENT

class UserChatAdapter : RecyclerView.Adapter<UserChatAdapter.UserChatViewHolder> {


    var chatMessages: List<ChatMessage>
    var senderID: String
    var receiverImage: String

    constructor(
        chatMessages: List<ChatMessage>,
        senderId: String,
        receiverImage: String
    ) : super() {
        this.chatMessages = chatMessages
        this.senderID = senderId
        this.receiverImage = receiverImage
    }


     open class UserChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         private fun getUserImage(encodedImage:String): Bitmap {
             val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
             return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
         }
         class ReceivedChatViewHolder(view:View): UserChatViewHolder(view){
             val message: TextView = view.findViewById(R.id.message_text)
             val dateTime: TextView = view.findViewById(R.id.message_dateTime)
             val imageReceiver: ImageView = view.findViewById(R.id.message_user_avatar)

         }

         class SentChatViewHolder(view:View):UserChatViewHolder(view){
             val message: TextView = view.findViewById(R.id.message_text)
             val dateTime: TextView = view.findViewById(R.id.message_dateTime)
         }

         fun bind(type:Int,view:View,position: Int,chatMessages: List<ChatMessage>,receiverImage: String){
             if(type == SENT){
                 SentChatViewHolder(view).message.text = chatMessages.get(position).message
                 SentChatViewHolder(view).dateTime.text = chatMessages.get(position).dateTime
             }else{
                 ReceivedChatViewHolder(view).message.text = chatMessages.get(position).message
                 ReceivedChatViewHolder(view).dateTime.text = chatMessages.get(position).dateTime
                 ReceivedChatViewHolder(view).imageReceiver.setImageBitmap(getUserImage(receiverImage))
             }
         }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserChatViewHolder {
        if (viewType == SENT) {
            val layout = R.layout.item_container_sent_message
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            return UserChatAdapter.UserChatViewHolder.SentChatViewHolder(view)
        } else {
            val layout = R.layout.item_container_received_message
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            return UserChatAdapter.UserChatViewHolder.ReceivedChatViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: UserChatViewHolder, position: Int) {
        holder.bind(holder.itemViewType,holder.itemView,position,chatMessages,receiverImage)
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun getItemViewType(position: Int): Int {
        if (senderID == chatMessages.get(position).senderID) {
            return SENT
        } else {
            return RECEIVED
        }
    }



}