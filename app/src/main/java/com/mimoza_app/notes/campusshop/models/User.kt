package com.mimoza_app.notes.campusshop.models

import java.io.Serializable

data class User(
    var name:String = "",
    var image:String = "",
    var email:String = "",
    var fcmToken:String = "",
    var uid:String = "",
    var surname:String =""
):Serializable

