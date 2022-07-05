package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mimoza_app.notes.campusshop.R

class MainItemViewHolder (val view: View): RecyclerView.ViewHolder(view){
    val tvTitle = view.findViewById<TextView>(R.id.card_title)
    val tvKorpus = view.findViewById<TextView>(R.id.card_building)
    val tvPrice = view.findViewById<TextView>(R.id.cardPriceValue)
    val ivPicture = view.findViewById<ImageView>(R.id.cardPhoto)
}