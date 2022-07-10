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



    var chatMessages:List<ChatMessage>
    var senderID:String
    var receiverImage:String

    constructor(chatMessages:List<ChatMessage>,senderId:String,receiverImage:String) : super(){
        this.chatMessages = chatMessages
        this.senderID = senderId
        this.receiverImage = receiverImage
    }



    class UserChatViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val message:TextView = view.findViewById(R.id.message_text)
        val dateTime:TextView = view.findViewById(R.id.message_dateTime)
        val imageReceiver:ImageView = view.findViewById(R.id.message_user_avatar)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserChatViewHolder {
        if(viewType == SENT){
            val layout = R.layout.item_container_sent_message
            val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
            return UserChatAdapter.UserChatViewHolder(view)
        }else{
            val layout = R.layout.item_container_received_message
            val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
            return UserChatAdapter.UserChatViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: UserChatViewHolder, position: Int) {
        if(holder.itemViewType == SENT){
            holder.message.text = chatMessages.get(position).message
            holder.dateTime.text = chatMessages.get(position).dateTime
        }else{
            holder.message.text = chatMessages.get(position).message
            holder.dateTime.text = chatMessages.get(position).dateTime
            holder.imageReceiver.setImageBitmap(getUserImage(receiverImage))
        }
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun getItemViewType(position: Int): Int {
        if(senderID ==chatMessages.get(position).senderID){
            return SENT
        }else{
            return RECEIVED
        }
    }

    private fun getUserImage(encodedImage:String): Bitmap {
        val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }


}