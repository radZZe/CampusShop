package com.mimoza_app.notes.campusshop.screens.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.util.*

class AddItemFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application
    fun initDatabase(type_connect: String, onSucces: () -> Unit) {
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(type_connect, { onSucces() }, {
            showToast(it)
        })
    }

    fun addItem(
        name: String,
        price: String,
        description: String,
        building: String,
        category: String,
        picture: String,
        idFirebase: String,

        ) {
        val database = FirebaseFirestore.getInstance()
        //val currentUser = Firebase.auth.currentUser?.uid.toString()
        val shopItem = hashMapOf<String, Any>()
        shopItem[KEY_NAME] = name
        shopItem[KEY_VALUE] = price
        shopItem[KEY_DESCRIPTION] = description
        shopItem[KEY_BUILDING] = building
        shopItem[KEY_CATEGORY] = category
        shopItem[KEY_IMAGE] = picture
        //shopItem[KEY_USER_ID] = currentUser
        shopItem[KEY_FIREBASE_ID] = idFirebase
        database.collection(KEY_COLLECTION_ITEMS)
            .add(shopItem)
            .addOnSuccessListener {
                showToast("Success")
            }
            .addOnFailureListener {
                showToast("Failed")
            }
    }
}