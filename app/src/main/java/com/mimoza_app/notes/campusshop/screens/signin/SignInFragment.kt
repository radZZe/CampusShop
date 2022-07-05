package com.mimoza_app.notes.campusshop.screens.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.campusshop.databinding.FragmentSignInBinding
import com.mimoza_app.notes.campusshop.util.*


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: SignInFragmentViewModel
    private lateinit var preferenceManager:PreferenceManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }
    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        mViewModel = ViewModelProvider(this)[SignInFragmentViewModel::class.java]
        mBinding.createAccount.setOnClickListener{
            signUp()
        }


    }

    private fun signUp() {
        val inputEmail = mBinding.inputEmail.text.toString()
        val inputPassword = mBinding.inputPassword.text.toString()
        val inputRepPass = mBinding.repPassInput.text.toString()
        if(isValidSignUp(inputEmail,inputPassword,inputRepPass)){
            mViewModel.signUp(inputEmail,inputPassword,preferenceManager)
        }
    }

    private fun isValidSignUp(email: String, pass: String, repPass: String): Boolean {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            EMAIL = email
            PASSWORD = pass
            if (PASSWORD == repPass) {
                return true
            } else {
                showToast("Пороли не совпадают")
                return false
            }
        } else {
            showToast("Введите логин и пороль")
            return false
        }
    }
}