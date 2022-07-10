package com.mimoza_app.notes.campusshop.screens.main.chat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.text.capitalize
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.User

class ChatAdapter:RecyclerView.Adapter<ChatAdapter.ChatHolder>{

    lateinit var users:List<User>
    lateinit var chatListener: ChatListener

    constructor(users: List<User>,chatListener: ChatListener) {
        this.users = users
        this.chatListener = chatListener
    }

    class ChatHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameUser: TextView = view.findViewById(R.id.user_name_field)
        val imageUser: ImageView = view.findViewById(R.id.user_avatar)
    }


    private fun getUserImage(encodedImage:String): Bitmap {
        val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val layout = R.layout.chat_item
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return ChatHolder(view)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.itemView.setOnClickListener {
            chatListener.onUserClicked(users.get(position))
        }
        holder.nameUser.text = "${users.get(position).name.capitalize()} ${users.get(position).surname.capitalize()}"
        holder.imageUser.setImageBitmap(getUserImage(users.get(position).image))
    }

    override fun getItemCount(): Int {
        return users.size
    }
}