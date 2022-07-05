package com.mimoza_app.notes.campusshop.screens.signin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.util.*

class SignInFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application
    fun initDatabase(type_connect:String,onSucces: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(type_connect,{onSucces()},{
            showToast(it)
        })
    }

    fun signUp(email:String,pass:String,manager: PreferenceManager) {
        val database = FirebaseFirestore.getInstance()
        val user = hashMapOf<String,Any>()
        user.put(KEY_EMAIL,email)
        user.put(KEY_PASSWORD,pass)
        database.collection(KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener {
                manager.putBoolean(KEY_IS_SIGNED_IN,true)
                manager.putString(KEY_USER_ID,it.id)
                manager.putString(KEY_EMAIL,email)
                manager.putString(KEY_PASSWORD,pass)
                APP_ACTIVITY.navController.navigate(R.id.action_signInFragment_to_main)
            }
            .addOnFailureListener {
                showToast(it.message)
            }
    }
}