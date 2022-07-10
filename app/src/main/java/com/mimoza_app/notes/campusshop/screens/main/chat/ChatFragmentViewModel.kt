package com.mimoza_app.notes.campusshop.screens.main.chat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.*

class ChatFragmentViewModel(application: Application) : AndroidViewModel(application) {
//    lateinit var listUsers:MutableLiveData<List<User>>
//    private lateinit var preferenceManager: PreferenceManager

//    fun initUsers():LiveData<List<User>>{
//        preferenceManager = PreferenceManager()
//        preferenceManager.PreferenceManager(APP_ACTIVITY)
//        val users = arrayListOf<User>()
//        val database = FirebaseFirestore.getInstance()
//        database.collection(KEY_COLLECTION_USERS).get()
//            .addOnCompleteListener {
//                val currentUserId = preferenceManager.getString(KEY_USER_ID)
//                if (it.isSuccessful && it.result != null){
//                    for(queryDocumentSnapshot: QueryDocumentSnapshot in it.result){
//                        if(currentUserId.equals(queryDocumentSnapshot.id)){
//                            continue
//                        }
//                        val user = User()
//                        user.name = queryDocumentSnapshot.getString(KEY_NAME).toString()
//                        user.email = queryDocumentSnapshot.getString(KEY_EMAIL).toString()
//                        user.image = queryDocumentSnapshot.getString(KEY_IMAGE).toString()
//                        user.token = queryDocumentSnapshot.getString(KEY_FCM_TOKEN).toString()
//                        users.add(user)
//                        listUsers.setValue(users)
//                    }
//                }
//                listUsers.setValue(users)
//            }
//        return listUsers
//    }
}