package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.ShopItem

class MainListAdapter: ListAdapter<ShopItem, MainItemViewHolder>(MainItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item_card, parent, false)
        return MainItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvTitle.text = shopItem.name
        holder.tvPrice.text = shopItem.price.toString()
        holder.tvKorpus.text = shopItem.building.toString()
        holder.ivPicture.setImageResource(shopItem.picture.toInt())
    // не уверен что будет работать
    // неообходимо протестировать после внедрения добавления объявлений
    }

    companion object {
        const val VIEW_TYPE = 1
        const val MAX_POOL_SIZE = 20
    }
}