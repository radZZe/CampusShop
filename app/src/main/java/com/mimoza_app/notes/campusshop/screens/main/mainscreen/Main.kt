package com.mimoza_app.notes.campusshop.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.ActivityMainBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentLoginBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentMainBinding
import com.mimoza_app.notes.campusshop.screens.MainActivity
import com.mimoza_app.notes.campusshop.screens.main.mainscreen.recycler_view.ShopListAdapter


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
            val shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE, ShopListAdapter.MAX_POOL_SIZE)
        }
        mBinding.rvShopList.layoutManager = GridLayoutManager(activity, 2)

    }


}