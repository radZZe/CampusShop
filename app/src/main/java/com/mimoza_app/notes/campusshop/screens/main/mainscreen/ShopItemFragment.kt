package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentShopItemBinding
import com.mimoza_app.notes.campusshop.models.ShopItem
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.util.*


class ShopItemFragment : Fragment() {

    private var _binding: FragmentShopItemBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var receiverItem: ShopItem
    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopItemBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        receiverItem = arguments?.get(KEY_ITEM) as ShopItem
        val userId = receiverItem.uid
        with (mBinding) {
            itemTitle.setText(receiverItem.name)
            actualPrice.setText(receiverItem.price)
            actualBuilding.setText(receiverItem.building)
            actualCategory.setText(receiverItem.category)
            itemDescription.setText(receiverItem.description)
        }
        decodeByteCode()

        database = FirebaseFirestore.getInstance()
        database.collection(KEY_COLLECTION_USERS).addSnapshotListener{snapshot, e ->
            if (e != null) {
                Log.d("ERROR", "Listen Failed")
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val docs = snapshot.documents
                docs.forEach {
                    val currUser = it.toObject<User>()
                    if (currUser?.uid == userId) {
                        val user = currUser
                        onUserClicked(user)
                    }
                }
            }
        }

        mBinding.btnBack.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_shopItemFragment_to_mainFragment)
        }

    }

    private fun decodeByteCode() {
        val decodedString: ByteArray = Base64.decode(receiverItem.image, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        mBinding.itemPhoto.setImageBitmap(decodedByte)
    }


    fun onUserClicked (user: User) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_USER, user)
        mBinding.btnTextSeller.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_shopItemFragment_to_userChat)
        }
    }
}