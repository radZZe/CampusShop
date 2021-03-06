package com.mimoza_app.notes.campusshop.screens.main.chat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.ChatMessage
import com.mimoza_app.notes.campusshop.util.*

class UserChatAdapter : RecyclerView.Adapter<UserChatAdapter.UserChatViewHolder> {


    var chatMessages: List<ChatMessage>
    var senderID: String
    var receiverImage: String
    lateinit var userChatListener: UserChatListener

    constructor(
        chatMessages: List<ChatMessage>,
        senderId: String,
        receiverImage: String,
        userChatListener: UserChatListener
    ) : super() {
        this.chatMessages = chatMessages
        this.senderID = senderId
        this.receiverImage = receiverImage
        this.userChatListener = userChatListener
    }



     open class UserChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         private fun getBitmapFromEncodedString(encodedImage:String): Bitmap {
             val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
             return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
         }
         class ReceivedChatViewHolder(view:View): UserChatViewHolder(view){
             val message: TextView = view.findViewById(R.id.message_text)
             val dateTime: TextView = view.findViewById(R.id.message_dateTime)
             val imageReceiver: ImageView = view.findViewById(R.id.message_user_avatar)

         }

         class ReceivedImageChatViewHolder(view:View):UserChatViewHolder(view){
             val message: ImageView = view.findViewById(R.id.message_image)
             val dateTime: TextView = view.findViewById(R.id.message_dateTime)
             val imageReceiver: ImageView = view.findViewById(R.id.message_user_avatar)
         }

         class SentChatViewHolder(view:View):UserChatViewHolder(view){
             val message: TextView = view.findViewById(R.id.message_text)
             val dateTime: TextView = view.findViewById(R.id.message_dateTime)
         }

         class SentImageChatViewHolder(view:View):UserChatViewHolder(view){
             val message: ImageView = view.findViewById(R.id.message_image)
             val dateTime: TextView = view.findViewById(R.id.message_dateTime)
         }

         fun bind(type:Int,view:View,position: Int,chatMessages: List<ChatMessage>,receiverImage: String){
             if(type == SENT_TEXT){
                 SentChatViewHolder(view).message.text = chatMessages.get(position).message
                 SentChatViewHolder(view).dateTime.text = chatMessages.get(position).dateTime
             }else if( type == SENT_IMAGE){
                 SentImageChatViewHolder(view).message.setImageURI(Uri.parse(chatMessages.get(position).message))
                 SentImageChatViewHolder(view).dateTime.text = chatMessages.get(position).dateTime
             }else if(type == RECEIVED_TEXT){
                 ReceivedChatViewHolder(view).message.text = chatMessages.get(position).message
                 ReceivedChatViewHolder(view).dateTime.text = chatMessages.get(position).dateTime
                 ReceivedChatViewHolder(view).imageReceiver.setImageBitmap(getBitmapFromEncodedString(receiverImage))
             }else if(type == RECEIVED_IMAGE){
                 ReceivedImageChatViewHolder(view).message.setImageURI(Uri.parse(chatMessages.get(position).message))
                 ReceivedImageChatViewHolder(view).dateTime.text = chatMessages.get(position).dateTime
                 ReceivedImageChatViewHolder(view).imageReceiver.setImageBitmap(getBitmapFromEncodedString(receiverImage))
             }
         }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserChatViewHolder {
        lateinit var viewHolder:UserChatViewHolder
        if (viewType == SENT_TEXT) {
            val layout = R.layout.item_container_sent_message
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            viewHolder =  UserChatAdapter.UserChatViewHolder.SentChatViewHolder(view)
        } else if(viewType == RECEIVED_TEXT) {
            val layout = R.layout.item_container_received_message
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            viewHolder = UserChatAdapter.UserChatViewHolder.ReceivedChatViewHolder(view)
        }else if(viewType == RECEIVED_IMAGE){
            val layout = R.layout.item_container_received_message_image
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            viewHolder = UserChatAdapter.UserChatViewHolder.ReceivedImageChatViewHolder(view)
        }else if(viewType == SENT_IMAGE){
            val layout = R.layout.item_container_sent_message_image
            val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
            viewHolder = UserChatAdapter.UserChatViewHolder.SentImageChatViewHolder(view)
        }
         return viewHolder
    }

    override fun onBindViewHolder(holder: UserChatViewHolder, position: Int) {
        holder.bind(holder.itemViewType,holder.itemView,position,chatMessages,receiverImage)
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun getItemViewType(position: Int): Int {
        if(chatMessages.get(position).type == MESSAGE_TYPE_IMAGE){
            if (senderID == chatMessages.get(position).senderID) {
                return SENT_IMAGE
            } else {
                return RECEIVED_IMAGE
            }
        }else{
            if (senderID == chatMessages.get(position).senderID) {
                return SENT_TEXT
            } else {
                return RECEIVED_TEXT
            }
        }
    }




}