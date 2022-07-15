package com.mimoza_app.notes.campusshop.models

import java.util.*

data class ChatMessage(
    var senderID:String ="",
    var recevierId:String ="",
    var message:String ="",
    var dateTime:String = "",
    var dateObject: Date = Date(),
<<<<<<< HEAD
    var isChecked:Boolean = false
=======
    var isChecked:Boolean = true
>>>>>>> 1433583a7fb301ede1ac8e04e03f713a0bdfce26

)
