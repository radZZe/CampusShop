package com.mimoza_app.notes.campusshop.screens.main.chat

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.campusshop.R

class ChatHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameUser: TextView = view.findViewById(R.id.user_name_field)
    val imageUser:ImageView = view.findViewById(R.id.user_avatar)
}