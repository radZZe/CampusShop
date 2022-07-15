package com.mimoza_app.notes.campusshop.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.util.*


class LoginFragmentViewModel(application: Application) : AndroidViewModel(application){
    private val mAuth = FirebaseAuth.getInstance()
    private val mContext = application
    fun initDatabase(type_connect:String,manager: PreferenceManager,onSucces: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(type_connect,{
            login(EMAIL, PASSWORD,manager)
            onSucces()
                                                  },{
            showToast(it)
        })
    }

    fun login(email:String,pass:String,manager:PreferenceManager){
        val uid = mAuth.currentUser!!.uid
        val database = FirebaseFirestore.getInstance()
        database.collection(KEY_COLLECTION_USERS)
            .whereEqualTo(KEY_UID,uid)
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful && it.getResult() != null
                    && it.getResult().documents.size > 0){
                    val documentSnapshot:DocumentSnapshot = it.getResult().documents.get(0)
                    manager.putBoolean(KEY_IS_SIGNED_IN,true)
                    manager.putString(KEY_USER_ID,documentSnapshot.id)
                    manager.putString(KEY_EMAIL, EMAIL)
                    manager.putString(KEY_NAME,documentSnapshot.getString(KEY_NAME).toString())
                    manager.putString(KEY_SURNAME,documentSnapshot.getString(KEY_SURNAME).toString())
                    manager.putString(KEY_IMAGE,documentSnapshot.getString(KEY_IMAGE).toString())
                    manager.putString(KEY_UID,uid)
                    APP_ACTIVITY.navController.navigate(R.id.action_loginFragment_to_mainFragment2)
                }else{
                    showToast("Ошибка входа")
                }
            }
    }

}