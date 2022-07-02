package com.mimoza_app.notes.campusshop.database

import com.google.firebase.auth.FirebaseAuth
import com.mimoza_app.notes.campusshop.util.*

class AppFirebaseRepository:DatabaseRepository {
    private val mAuth = FirebaseAuth.getInstance()
    override fun signOut() {
        mAuth.signOut()
    }
    override fun connectToDatabase(type_connect:String,onSuccess: () -> Unit, onFail: (String) -> Unit){
        if(type_connect == LOGIN){
            mAuth.signInWithEmailAndPassword(EMAIL,PASSWORD)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { showToast("Ошибка входа") }

        }else if (type_connect == SIGNUP){
            mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFail(it.message.toString()) }
        }
    }

}