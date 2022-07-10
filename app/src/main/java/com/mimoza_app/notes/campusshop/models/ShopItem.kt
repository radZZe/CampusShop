package com.mimoza_app.notes.campusshop.models

data class ShopItem(val name: String = "",
                    val price: Int = 0,
                    val description: String = "",
                    val building: Double = 0.0,
                    val category: String = "",
                    val picture: String,
                    val itemId: String = "",
                    )
{
    companion object{
        const val UNDEFINED_ID = -1
    }
}