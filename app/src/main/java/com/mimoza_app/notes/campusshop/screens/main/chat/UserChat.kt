package com.mimoza_app.notes.campusshop.screens.main.chat

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.gu.toolargetool.TooLargeTool
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentUserChatBinding
import com.mimoza_app.notes.campusshop.models.ChatMessage
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.screens.CropperAcrtivity
import com.mimoza_app.notes.campusshop.util.*
<<<<<<< HEAD
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
=======
>>>>>>> 51b399e43a1aefc030809119b41109496b8fb2e1
import java.util.*


@Suppress("DEPRECATION")
class UserChat : Fragment(),UserChatListener {


    private var _binding: FragmentUserChatBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var chatMessages :ArrayList<ChatMessage>
    private lateinit var preferenceManager:PreferenceManager
    private lateinit var receiverUser:User
    private lateinit var database:FirebaseFirestore
    private lateinit var userChatAdpater:UserChatAdapter
    private lateinit var senderId:String
    private lateinit var mGetContent:ActivityResultLauncher<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserChatBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGetContent=registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                val intent = Intent(APP_ACTIVITY,CropperAcrtivity::class.java)
                intent.putExtra("DATA",it.toString())
                startActivityForResult(intent,101)
            })
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
        message.put(KEY_TYPE_MESSAGE, MESSAGE_TYPE_TEXT)
        database.collection(KEY_COLLECTION_USERS)
            .whereEqualTo(KEY_UID,preferenceManager.getString(KEY_UID))
            .get().addOnCompleteListener {
                it.getResult().documents.get(0).reference.collection(KEY_COLLECTION_CHAT).add(message)
            }
        mBinding.typeMessageField.setText(null)
    }
    private fun sendMessageAsImage(image:Uri){
        val message = hashMapOf<String,Any>()
        message.put(KEY_IS_CHECKED,false)
        message.put(SENDER_ID, preferenceManager.getString(KEY_USER_ID)!!)
        message.put(RECEIVER_ID,receiverUser.id)
        message.put(KEY_MESSAGE,image)
        message.put(KEY_TIMESTAMP,Date())
        message.put(KEY_TYPE_MESSAGE, MESSAGE_TYPE_IMAGE)
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
        userChatAdpater = UserChatAdapter(chatMessages,senderId,receiverUser.image,)
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
            mGetContent.launch("image/*")
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == -1 && requestCode == 101){
            var result = data!!.getStringExtra("RESULT")
            var resultUri: Uri = Uri.parse(result)
            if (resultUri!=null){
                sendMessageAsImage(resultUri)
            }
        }
    }


    private fun encodedImage(bitmap:Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) //bm is the bitmap object
        val b: ByteArray = baos.toByteArray()
        var encodedImage = Base64.encodeToString(b,Base64.DEFAULT)
        return encodedImage
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
                                    chatMessage.type = documentChange.document.getString(KEY_TYPE_MESSAGE)!!
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
                                    chatMessage.type = documentChange.document.getString(KEY_TYPE_MESSAGE)!!
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

    override fun onMessageClicked() {
        TODO("Not yet implemented")
    }


}