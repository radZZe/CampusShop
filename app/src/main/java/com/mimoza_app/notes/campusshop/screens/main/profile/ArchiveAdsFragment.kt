package com.mimoza_app.notes.campusshop.screens.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentActiveAdsBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentArchiveAdsBinding
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY

class ArchiveAdsFragment : Fragment() {

    private var _binding: FragmentArchiveAdsBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArchiveAdsBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mBinding.btnBack.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_archiveAdsFragment_to_profileFragment)
        }
    }

}