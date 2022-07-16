package com.mimoza_app.notes.campusshop.screens.signin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.util.*

class SignInFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mAuth = FirebaseAuth.getInstance()
    private val mContext = application
    fun initDatabase(type_connect:String,manager:PreferenceManager,name:String,surname:String,image:String,onSucces: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(type_connect,{
            signUp(EMAIL, PASSWORD,manager,name,surname,image)
            onSucces() },{
            showToast(it)
        })
    }

    fun signUp(email:String,pass:String,manager: PreferenceManager,name:String,surname:String,image:String) {
        val uid = mAuth.currentUser!!.uid
        val database = FirebaseFirestore.getInstance()
        val user = hashMapOf<String,Any>()
        user.put(KEY_UID,uid)
        user.put(KEY_NAME,name)
        user.put(KEY_SURNAME,surname)
        user.put(KEY_IMAGE,image)
        database.collection(KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener {
                manager.putBoolean(KEY_IS_SIGNED_IN,true)
                manager.putString(KEY_USER_ID,it.id)
                manager.putString(KEY_EMAIL,email)
                manager.putString(KEY_PASSWORD,pass)
                manager.putString(KEY_NAME,name)
                manager.putString(KEY_SURNAME,surname)
                manager.putString(KEY_IMAGE,image)
                manager.putString(KEY_UID,uid)
                APP_ACTIVITY.navController.navigate(R.id.action_signInFragment_to_main)
            }
            .addOnFailureListener {
                showToast(it.message)
            }

    }
}