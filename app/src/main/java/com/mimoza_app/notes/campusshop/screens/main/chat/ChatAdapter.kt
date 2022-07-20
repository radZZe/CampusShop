package com.mimoza_app.notes.campusshop.screens.main.chat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.*

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    lateinit var users: List<User>
    lateinit var chatListener: ChatListener
    lateinit var manager: PreferenceManager

    constructor(
        users: List<User>,
        chatListener: ChatListener,
        manager: PreferenceManager,
    ) {
        this.users = users
        this.chatListener = chatListener
        this.manager = manager
    }

    class ChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameUser: TextView = view.findViewById(R.id.user_name_field)
        val imageUser: ImageView = view.findViewById(R.id.user_avatar)
        val unreadMessageCount: TextView = view.findViewById(R.id.unread_msg)
        val unreadMessageLayout: FrameLayout = view.findViewById(R.id.unread_msg_layout)
    }


    private fun getUserImage(encodedImage: String): Bitmap {
        val bytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val layout = R.layout.chat_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ChatHolder(view)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.itemView.setOnClickListener {
            chatListener.onUserClicked(users.get(position))
        }
        holder.nameUser.text =
            "${users.get(position).name.capitalize()} ${users.get(position).surname.capitalize()}"
        holder.imageUser.setImageBitmap(getUserImage(users.get(position).image))

        var count = 0
        var user = users.get(position)
        val database = FirebaseFirestore.getInstance().collection(KEY_COLLECTION_USERS).document(user.uid).collection(KEY_COLLECTION_CHAT).get()
        database.addOnCompleteListener {
            if ( it.isSuccessful && (it.getResult() != null) &&  (it.getResult().documents.size > 0)) {
                it.getResult().documents.forEach{
                    if((it.getBoolean(KEY_IS_CHECKED) == false )
                        && it.getString(RECEIVER_ID) == manager.getString(KEY_USER_ID)){
                        count += 1
                    }
                }
            }
            if(count>0){
                holder.unreadMessageCount.text = "${count}"
                holder.unreadMessageLayout.visibility = View.VISIBLE
            }else{
                holder.unreadMessageCount.text = ""
            }
        }
    }


    override fun getItemCount(): Int {
        return users.size
    }
}