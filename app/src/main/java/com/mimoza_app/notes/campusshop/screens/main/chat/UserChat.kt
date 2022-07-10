package com.mimoza_app.notes.campusshop.screens.main.chat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentUserChatBinding
import com.mimoza_app.notes.campusshop.models.ChatMessage
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY
import com.mimoza_app.notes.campusshop.util.KEY_IMAGE
import com.mimoza_app.notes.campusshop.util.KEY_USER
import com.mimoza_app.notes.campusshop.util.PreferenceManager


class UserChat : Fragment() {


    private var _binding: FragmentUserChatBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var chatMessages :List<ChatMessage>
    private lateinit var preferenceManager:PreferenceManager
    private lateinit var receiverUser:User
    private lateinit var database:FirebaseFirestore
    private lateinit var userChatAdpater:UserChatAdapter
    private lateinit var senderId:String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserChatBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        chatMessages = arrayListOf<ChatMessage>()
        senderId = preferenceManager.getString(KEY_USER)!!
        receiverUser = arguments?.get(KEY_USER) as User
        userChatAdpater = UserChatAdapter(chatMessages,senderId,receiverUser.image)
        database = FirebaseFirestore.getInstance()
        val bytes =Base64.decode(receiverUser.image,Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        mBinding.rvUserChat.adapter = userChatAdpater
        mBinding.userAvatar.setImageBitmap(bitmap)
        mBinding.userNameSurnameField.setText("${receiverUser.name.capitalize()} ${receiverUser.surname.capitalize()}")

    }


    private fun getBitmapFromEncodedString(encodedImage:String): Bitmap {
        val bytes = Base64.decode(encodedImage,Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }

}