package com.mimoza_app.notes.campusshop.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.util.*

class LoginFragmentViewModel(application: Application) : AndroidViewModel(application){
    private val mContext = application
    fun initDatabase(type_connect:String,onSucces: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(type_connect,{onSucces()},{
            showToast(it)
        })
    }

    fun login(email:String,pass:String,manager:PreferenceManager){
        val database = FirebaseFirestore.getInstance()
        database.collection(KEY_COLLECTION_USERS)
            .whereEqualTo(KEY_EMAIL,email)
            .whereEqualTo(KEY_PASSWORD,pass)
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful && it.getResult() != null
                    && it.getResult().documents.size > 0){
                    val documentSnapshot:DocumentSnapshot = it.getResult().documents.get(0)
                    manager.putBoolean(KEY_IS_SIGNED_IN,true)
                    manager.putString(KEY_USER_ID,documentSnapshot.id)
                    manager.putString(KEY_EMAIL, documentSnapshot.getString(KEY_EMAIL).toString())
                    APP_ACTIVITY.navController.navigate(R.id.action_loginFragment_to_mainFragment2)
                }else{
                    showToast("Ошибка входа")
                }
            }
    }

}