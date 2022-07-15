package com.mimoza_app.notes.campusshop.screens.main.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.*

class ChatsFragment : Fragment(),ChatListener {

    private var _binding: FragmentChatsBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var rvChat:RecyclerView
    private lateinit var  chatAdapter:ChatAdapter
    private lateinit var mViewModel:ChatFragmentViewModel
    private lateinit var preferenceManager: PreferenceManager


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
        getUsers()
    }


    fun getUsers() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        val database = FirebaseFirestore.getInstance()
        database.collection(KEY_COLLECTION_USERS).get()
            .addOnCompleteListener {
                val currentUserId = preferenceManager.getString(KEY_USER_ID)
                if (it.isSuccessful && it.result != null){
                    val users = arrayListOf<User>()
                    for(queryDocumentSnapshot: QueryDocumentSnapshot in it.result){
                        if(currentUserId.equals(queryDocumentSnapshot.id)){
                            continue
                        }
                        val user = User()
                        user.name = queryDocumentSnapshot.getString(KEY_NAME).toString()
                        user.email = queryDocumentSnapshot.getString(KEY_EMAIL).toString()
                        user.image = queryDocumentSnapshot.getString(KEY_IMAGE).toString()
                        user.token = queryDocumentSnapshot.getString(KEY_FCM_TOKEN).toString()
                        user.surname = queryDocumentSnapshot.getString(KEY_SURNAME).toString()
                        user.id = queryDocumentSnapshot.id
                        users.add(user)
                    }
                    if(users.size > 0){
                        chatAdapter = ChatAdapter(users, this, preferenceManager)
                        mBinding.rvChat.adapter = chatAdapter
                    }else{
                        showToast("Ошибка загрузки")
                    }
                }else{
                    showToast("Ошибка загрузки")
                }
            }
    }

    override fun onUserClicked(user: User) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_USER,user)
        APP_ACTIVITY.navController.navigate(R.id.action_chatsFragment_to_userChat,bundle)
    }

}