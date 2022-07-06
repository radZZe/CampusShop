package com.mimoza_app.notes.campusshop.screens.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
<<<<<<< HEAD
=======
import com.mimoza_app.notes.campusshop.util.PreferenceManager
>>>>>>> 5231ea0ce307ac6ddf6f07ef8fe8ab8fd631ae05
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