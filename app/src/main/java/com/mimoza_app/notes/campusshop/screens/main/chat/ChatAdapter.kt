package com.mimoza_app.notes.campusshop.screens.main.chat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.User

class ChatAdapter: ListAdapter<User, ChatHolder>(ChatItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val layout = R.layout.chat_item
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return ChatHolder(view)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.nameUser.text = getItem(position).name
        holder.imageUser.setImageBitmap(getUserImage(getItem(position).image))
    }

    override fun onViewAttachedToWindow(holder: ChatHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ChatHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    private fun getUserImage(encodedImage:String): Bitmap {
        val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }
}