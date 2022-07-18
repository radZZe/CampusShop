package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import androidx.cardview.widget.CardView
import com.mimoza_app.notes.campusshop.models.ShopItem

interface ShopItemListener {
    fun onShopItemClicked(shopItem: ShopItem)
}