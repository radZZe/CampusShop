package com.mimoza_app.notes.campusshop.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentChatsBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentLoginBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentMainBinding

class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatsBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }


}