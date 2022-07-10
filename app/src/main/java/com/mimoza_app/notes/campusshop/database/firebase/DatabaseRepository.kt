package com.mimoza_app.notes.campusshop.database.firebase

import androidx.lifecycle.LiveData
import com.mimoza_app.notes.campusshop.models.ShopItem

interface DatabaseRepository {
    fun signOut() {}
    fun connectToDatabase(type_connect:String,onSuccess: () -> Unit, onFail: (String) -> Unit){}

}