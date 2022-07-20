package com.mimoza_app.notes.campusshop.screens.main.chat

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentUserChatBinding
import com.mimoza_app.notes.campusshop.models.ChatMessage
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.*
import java.util.*


class UserChat : Fragment() {


    private var _binding: FragmentUserChatBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var chatMessages :ArrayList<ChatMessage>
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

    private fun sendMessage(){
        val message = hashMapOf<String,Any>()
        message.put(KEY_IS_CHECKED,false)
        message.put(SENDER_ID, preferenceManager.getString(KEY_USER_ID)!!)
        message.put(RECEIVER_ID,receiverUser.uid)
        message.put(KEY_MESSAGE,mBinding.typeMessageField.text.toString())
        message.put(KEY_TIMESTAMP,Date())
        database.collection(KEY_COLLECTION_USERS)
            .whereEqualTo(KEY_UID,preferenceManager.getString(KEY_UID))
            .get().addOnCompleteListener {
                it.getResult().documents.get(0).reference.collection(KEY_COLLECTION_CHAT).add(message)
            }
        mBinding.typeMessageField.setText(null)
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        chatMessages = arrayListOf<ChatMessage>()
        senderId = preferenceManager.getString(KEY_USER_ID)!!
        receiverUser = arguments?.get(KEY_USER) as User
        userChatAdpater = UserChatAdapter(chatMessages,senderId,receiverUser.image)
        database = FirebaseFirestore.getInstance()
        listenMessage()
        val bytes = Base64.decode(receiverUser.image,Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        mBinding.rvUserChat.adapter = userChatAdpater
        mBinding.userAvatar.setImageBitmap(bitmap)
        mBinding.userNameSurnameField.setText("${receiverUser.name.capitalize()} ${receiverUser.surname.capitalize()}")
        mBinding.sendBtn.setOnClickListener{
            sendMessage()
        }
        mBinding.backButton.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_userChat_to_chatsFragment)
        }
        mBinding.attachBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type ="*/*"
            pickFiles.launch(intent);
//            val intent = Intent(Intent.ACTION_PICK, )
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            pickImage.launch(intent)
        }

    }


    private fun getBitmapFromEncodedString(encodedImage:String): Bitmap {
        val bytes = Base64.decode(encodedImage,Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }

    private fun getReadableDateTime(date:Date):String{
        return SimpleDateFormat("MMMM dd, yyyy - hh:mm a",Locale.getDefault()).format(date)
    }

    private fun listenMessage(){
        database.collection(KEY_COLLECTION_USERS)
            .whereEqualTo(KEY_UID,preferenceManager.getString(KEY_UID))
            .get().addOnCompleteListener {
                it.getResult().documents.get(0).reference.collection(KEY_COLLECTION_CHAT)
                    .whereEqualTo(SENDER_ID,preferenceManager.getString(KEY_USER_ID).toString())
                    .whereEqualTo(RECEIVER_ID,receiverUser.uid)
                    .addSnapshotListener{ value, error ->
                        if(error != null){
                            return@addSnapshotListener
                        }
                        if(value != null){
                            var count = chatMessages.size
                            for( documentChange in value.documentChanges){
                                if(documentChange.type == DocumentChange.Type.ADDED){
                                    var chatMessage = ChatMessage()
                                    chatMessage.isChecked = false
                                    chatMessage.senderID = documentChange.document.getString(SENDER_ID)!!
                                    chatMessage.recevierId = documentChange.document.getString(RECEIVER_ID)!!
                                    chatMessage.message = documentChange.document.getString(KEY_MESSAGE)!!
                                    chatMessage.dateTime = getReadableDateTime(
                                        documentChange.document.getDate(
                                            KEY_TIMESTAMP)!!
                                    )
                                    chatMessage.dateObject = documentChange.document.getDate(KEY_TIMESTAMP)!!
                                    chatMessages.add(chatMessage)
                                }
                            }
                            val chatComparator = Comparator { obj1:ChatMessage,obj2:ChatMessage -> obj1.dateObject.compareTo(obj2.dateObject)}
                            Collections.sort(chatMessages,chatComparator)
                            if(count == 0){
                                userChatAdpater.notifyDataSetChanged()
                            }else{
                                userChatAdpater.notifyItemRangeInserted(chatMessages.size,chatMessages.size)
                                mBinding.rvUserChat.smoothScrollToPosition(chatMessages.size - 1)
                            }
                        }
                    }
            }
        database.collection(KEY_COLLECTION_USERS)
            .document(receiverUser.uid)
            .get()
            .addOnCompleteListener {
                it.getResult().reference.collection(KEY_COLLECTION_CHAT)
                    .whereEqualTo(SENDER_ID,receiverUser.uid)
                    .whereEqualTo(RECEIVER_ID,preferenceManager.getString(KEY_USER_ID).toString())
                    .addSnapshotListener{ value, error ->
                        if(error != null){
                            return@addSnapshotListener
                        }
                        if(value != null){
                            var count = chatMessages.size
                            value.documents.forEach{
                                it.reference.update(KEY_IS_CHECKED,true)
                            }
                            for( documentChange in value.documentChanges){
                                if(documentChange.type == DocumentChange.Type.ADDED){
                                    var chatMessage = ChatMessage()
                                    chatMessage.senderID = documentChange.document.getString(SENDER_ID)!!
                                    chatMessage.recevierId = documentChange.document.getString(RECEIVER_ID)!!
                                    chatMessage.message = documentChange.document.getString(KEY_MESSAGE)!!
                                    chatMessage.dateTime = getReadableDateTime(
                                        documentChange.document.getDate(
                                            KEY_TIMESTAMP)!!
                                    )
                                    chatMessage.dateObject = documentChange.document.getDate(KEY_TIMESTAMP)!!
                                    chatMessages.add(chatMessage)
                                }
                            }
                            val chatComparator = Comparator { obj1:ChatMessage,obj2:ChatMessage -> obj1.dateObject.compareTo(obj2.dateObject)}
                            Collections.sort(chatMessages,chatComparator)
                            if(count == 0){
                                userChatAdpater.notifyDataSetChanged()
                            }else{
                                userChatAdpater.notifyItemRangeInserted(chatMessages.size,chatMessages.size)
                                mBinding.rvUserChat.smoothScrollToPosition(chatMessages.size - 1)
                            }
                        }
                    }
            }

    }

    val pickFiles: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            if(it.data != null){
                it.data!!.data
            }
        }
    }

}