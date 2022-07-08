package com.mimoza_app.notes.campusshop.screens.main.chat

import androidx.recyclerview.widget.DiffUtil
import com.mimoza_app.notes.campusshop.models.User

class ChatItemDiffCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.token == newItem.token
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}