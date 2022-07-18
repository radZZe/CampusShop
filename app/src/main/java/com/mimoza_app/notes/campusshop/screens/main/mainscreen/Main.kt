package com.mimoza_app.notes.campusshop.screens.main

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import com.mimoza_app.notes.campusshop.databinding.FragmentMainBinding
import com.mimoza_app.notes.campusshop.models.ShopItem
import com.mimoza_app.notes.campusshop.models.User
import com.mimoza_app.notes.campusshop.screens.main.mainscreen.MainListAdapter
import com.mimoza_app.notes.campusshop.screens.main.mainscreen.ShopItemListener
import com.mimoza_app.notes.campusshop.util.*


class main : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var rvShopList: RecyclerView
    private lateinit var database: FirebaseFirestore
    private lateinit var adapter: MainListAdapter
    private lateinit var itemsArrayList: ArrayList<ShopItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        rvShopList = mBinding.rvShopList
        with (rvShopList) {
            recycledViewPool.setMaxRecycledViews(MainListAdapter.VIEW_TYPE, MainListAdapter.MAX_POOL_SIZE)
            layoutManager = GridLayoutManager(APP_ACTIVITY, 2)
            setHasFixedSize(true)
        }

        itemsArrayList = arrayListOf()
        adapter = MainListAdapter(itemsArrayList, object: ShopItemListener{
            override fun onShopItemClicked(shopItem: ShopItem) {
                val bundle = Bundle()
                bundle.putSerializable(KEY_ITEM, shopItem)
                APP_ACTIVITY.navController.navigate(com.mimoza_app.notes.campusshop.R.id.action_mainFragment_to_shopItemFragment,bundle)
            }
        })
        rvShopList.adapter = adapter

        getData()

        mBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.getFilter()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText);
                return true
            }

        })
    }

    private fun getData() {
        database = FirebaseFirestore.getInstance()
        database.collection(KEY_COLLECTION_ITEMS)
            .addSnapshotListener(object : EventListener<QuerySnapshot>{

                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                    if (error != null) {
                        Log.d("Firestore Error", error.message.toString())
                        return
                    } else {
                        for (dc: DocumentChange in value?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                itemsArrayList.add(dc.document.toObject(ShopItem::class.java))
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            })
    }

//    override fun onShopItemClicked (shopItem: ShopItem) {
//        val bundle = Bundle()
//        bundle.putSerializable(KEY_ITEM, shopItem)
//        APP_ACTIVITY.navController.navigate(com.mimoza_app.notes.campusshop.R.id.action_mainFragment_to_shopItemFragment,bundle)
//    }

}