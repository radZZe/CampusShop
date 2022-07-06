package com.mimoza_app.notes.campusshop.screens.main.profile

import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeByteArray
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.mimoza_app.notes.campusshop.R
<<<<<<< HEAD
import com.mimoza_app.notes.campusshop.database.firebase.AppFirebaseRepository
import com.mimoza_app.notes.campusshop.databinding.FragmentMainBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentProfileBinding
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY
import com.mimoza_app.notes.campusshop.util.showToast
=======
import com.mimoza_app.notes.campusshop.databinding.FragmentProfileBinding
import com.mimoza_app.notes.campusshop.util.*
>>>>>>> 5231ea0ce307ac6ddf6f07ef8fe8ab8fd631ae05

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: ProfileFragmentViewModel
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
        loadUserDetails()
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        mViewModel = ViewModelProvider(this)[ProfileFragmentViewModel::class.java]
        mBinding.btnAdd.setOnClickListener{
            APP_ACTIVITY.navController.navigate(R.id.action_profileFragment_to_addItemInShopFragment)
        }
        mBinding.btnSettings.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        mBinding.btnExit.setOnClickListener {
            showToast("Succesfuly signed out")
            APP_ACTIVITY.navController.navigate(R.id.action_profileFragment_to_loginFragment)
        }
    }

<<<<<<< HEAD
=======
    fun loadUserDetails(){
        mBinding.userEmailField.text = preferenceManager.getString(KEY_EMAIL)
        mBinding.userNameField.text = "${preferenceManager.getString(KEY_NAME)?.capitalize()} " +
                "${preferenceManager.getString(KEY_SURNAME)?.capitalize()}"
        val bytes = Base64.decode(preferenceManager.getString(KEY_IMAGE),Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
        mBinding.userAvatar.setImageBitmap(bitmap)
    }
>>>>>>> 5231ea0ce307ac6ddf6f07ef8fe8ab8fd631ae05
}