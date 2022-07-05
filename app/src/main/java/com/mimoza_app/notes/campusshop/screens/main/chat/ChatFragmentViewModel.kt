//package com.mimoza_app.notes.campusshop.screens.main.chat
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.mimoza_app.notes.campusshop.util.showToast
//
//class ChatFragmentViewModel(application: Application) : AndroidViewModel(application) {
//    private val mContext = application
//    fun addDataToFirestore(){
//        val database = FirebaseFirestore.getInstance()
//        val data =  hashMapOf<String, Any>()
//        data.put("email","example@gmail.com")
//        database.collection("users")
//            .add(data)
//            .addOnSuccessListener {
//                showToast("Data inserted")
//            }
//            .addOnFailureListener {
//                showToast("Error addDataToFirestore")
//            }
//    }
//}