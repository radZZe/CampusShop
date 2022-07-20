package com.mimoza_app.notes.campusshop.util

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var REF_STORAGE_ROOT:StorageReference


const val FOLDER_MESSAGE_IMAGE = "folder_message_image"


//20 урок 13:04 пример того что нужно будет перенести в этот файл

fun initStorage(){
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}