package com.mimoza_app.notes.campusshop.screens.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentAddItemInShopBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding

class AddItemInShopFragment : Fragment() {

    private var _binding: FragmentAddItemInShopBinding? = null
    private val mBinding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemInShopBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

}