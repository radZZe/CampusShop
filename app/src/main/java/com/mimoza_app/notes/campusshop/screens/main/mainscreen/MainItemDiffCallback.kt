package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import androidx.recyclerview.widget.DiffUtil
import com.mimoza_app.notes.campusshop.models.ShopItem

class MainItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}