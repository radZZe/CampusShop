package com.mimoza_app.notes.campusshop.screens.main.profile

import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentAddItemInShopBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding

class AddItemInShopFragment : Fragment() {

    private var _binding: FragmentAddItemInShopBinding? = null
    private val mBinding get() =_binding!!
    private lateinit var path: String
    private lateinit var uri: Uri
    private lateinit var captureImage: ImageView



    override fun onResume() {
        super.onResume()
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.categories_dropdown_item, categories)
        mBinding.autoCompleteTextView.setAdapter(arrayAdapter)
        captureImage = mBinding.imgAddImage
        mBinding.btnAddImage.setOnClickListener {
            mBinding.btnAddImage.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemInShopBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

}