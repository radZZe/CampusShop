package com.mimoza_app.notes.campusshop.models

import java.io.Serializable

data class User(
    var name:String = "",
    var image:String = "",
    var email:String = "",
    var token:String = "",
    var id:Int = 0,
    var surname:String =""
):Serializable
