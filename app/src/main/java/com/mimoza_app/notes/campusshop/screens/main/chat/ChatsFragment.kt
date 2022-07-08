package com.mimoza_app.notes.campusshop.screens.main.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.*

class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var rvChat:RecyclerView
    private lateinit var  chatAdapter:ChatAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatsBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        getUsers()
        mBinding.backpressed.setOnClickListener{
            showToast("TODO onBackPressed")
        }
    }

    private fun getUsers(){
        val database = FirebaseFirestore.getInstance()
        database.collection(KEY_COLLECTION_USERS).get()
            .addOnCompleteListener {
                val currentUserId = preferenceManager.getString(KEY_USER_ID)
                if (it.isSuccessful && it.result != null){
                    val users = arrayListOf<User>()
                    for(queryDocumentSnapshot:QueryDocumentSnapshot in it.result){
                        if(currentUserId.equals(queryDocumentSnapshot.id)){
                            continue
                        }
                        val user = User()
                        user.name = queryDocumentSnapshot.getString(KEY_NAME).toString()
                        user.email = queryDocumentSnapshot.getString(KEY_EMAIL).toString()
                        user.image = queryDocumentSnapshot.getString(KEY_IMAGE).toString()
                        user.token = queryDocumentSnapshot.getString(KEY_FCM_TOKEN).toString()
                        users.add(user)
                    }
                    if(users.size > 0){
                        setupRecyclerView()
                    }
                }
            }
    }

    private fun setupRecyclerView(){
        rvChat = mBinding.rvChat
        with(rvChat){
            chatAdapter = ChatAdapter()
            adapter = chatAdapter
        }
    }


}