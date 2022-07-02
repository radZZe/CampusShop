package com.mimoza_app.notes.campusshop.database

import androidx.lifecycle.LiveData

interface DatabaseRepository {
    fun signOut() {}
    fun connectToDatabase(type_connect:String,onSuccess: () -> Unit, onFail: (String) -> Unit){}
}