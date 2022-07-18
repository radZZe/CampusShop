package com.mimoza_app.notes.campusshop.screens.main.mainscreen

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentShopItemBinding
import com.mimoza_app.notes.campusshop.models.ShopItem
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY
import com.mimoza_app.notes.campusshop.util.KEY_ITEM


class ShopItemFragment : Fragment() {

    private var _binding: FragmentShopItemBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var receiverItem: ShopItem

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
        setData()
    }

    private fun initialization() {
        mBinding.btnBack.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_shopItemFragment_to_mainFragment)
        }
        mBinding.btnTextSeller.setOnClickListener {
            // APP_ACTIVITY.navController.navigate(R.id.)
        }
    }

    private fun setData() {
        receiverItem = arguments?.get(KEY_ITEM) as ShopItem
        with (mBinding) {
            itemTitle.setText(receiverItem.name)
            actualPrice.setText(receiverItem.price)
            actualBuilding.setText(receiverItem.building)
            actualCategory.setText(receiverItem.category)
            itemDescription.setText(receiverItem.description)
        }
        decodeByteCode()
    }

    private fun decodeByteCode() {
        val decodedString: ByteArray = Base64.decode(receiverItem.picture, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        mBinding.itemPhoto.setImageBitmap(decodedByte)
    }

}