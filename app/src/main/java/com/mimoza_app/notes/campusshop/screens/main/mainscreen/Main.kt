package com.mimoza_app.notes.campusshop.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mimoza_app.notes.campusshop.databinding.FragmentMainBinding
import com.mimoza_app.notes.campusshop.screens.main.mainscreen.MainListAdapter


class main : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rvShopList = mBinding.rvShopList
        with(rvShopList) {
            val mainListAdapter = MainListAdapter()
            adapter = mainListAdapter
            recycledViewPool.setMaxRecycledViews(MainListAdapter.VIEW_TYPE, MainListAdapter.MAX_POOL_SIZE)
        }
        mBinding.rvShopList.layoutManager = GridLayoutManager(activity, 2)

    }


}