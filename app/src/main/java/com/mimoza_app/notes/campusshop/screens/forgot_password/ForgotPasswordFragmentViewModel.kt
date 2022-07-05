package com.mimoza_app.notes.campusshop.screens.forgot_password

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository

class ForgotPasswordFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application
    fun resetPassword(email:String,onSuccess: () -> Unit){
        val rep = AppFirebaseRepository()
        rep.resetPassword(email,onSuccess)
    }

}