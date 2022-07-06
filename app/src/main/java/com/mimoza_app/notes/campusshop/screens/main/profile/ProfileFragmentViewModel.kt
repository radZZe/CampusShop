package com.mimoza_app.notes.campusshop.screens.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.util.PreferenceManager
import com.mimoza_app.notes.campusshop.util.REPOSITORY
import com.mimoza_app.notes.campusshop.util.showToast

class ProfileFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application

    fun initDatabase(type_connect:String,onSucces: () -> Unit){
        REPOSITORY = AppFirebaseRepository()
        REPOSITORY.connectToDatabase(type_connect,{onSucces()},{
            showToast(it)
        })
    }

}