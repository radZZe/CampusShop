package com.mimoza_app.notes.campusshop.screens.main.mainscreen.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.ShopItem

class ShopListAdapter: ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item_card, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvTitle.text = shopItem.name
        holder.tvPrice.text = shopItem.price.toString()
        holder.tvKorpus.text = shopItem.building.toString()
        holder.ivPicture.setImageResource(shopItem.picture)
    }

    companion object {
        const val VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 20
    }
}