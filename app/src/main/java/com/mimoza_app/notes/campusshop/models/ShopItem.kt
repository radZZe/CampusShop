package com.mimoza_app.notes.campusshop.models

data class ShopItem(val name: String,
                    val price: Int,
                    val description: String = "",
                    val building: Float,
                    val category: String,
                    val id: Int,
                    val picture: Int)
{
    companion object{
        const val UNDEFINED_ID = -1
    }
}