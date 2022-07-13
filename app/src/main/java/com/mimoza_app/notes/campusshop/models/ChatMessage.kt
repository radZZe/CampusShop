package com.mimoza_app.notes.campusshop.models

import java.util.*

data class ChatMessage(
    var senderID:String ="",
    var recevierId:String ="",
    var message:String ="",
    var dateTime:String = "",
    var dateObject: Date = Date(),
    var isChecked:Boolean = true

)
