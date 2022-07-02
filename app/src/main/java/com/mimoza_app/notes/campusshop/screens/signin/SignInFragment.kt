package com.mimoza_app.notes.campusshop.screens.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentLoginBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentSignInBinding
import com.mimoza_app.notes.campusshop.screens.login.LoginFragmentViewModel
import com.mimoza_app.notes.campusshop.util.*


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: SignInFragmentViewModel
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
        mViewModel = ViewModelProvider(this)[SignInFragmentViewModel::class.java]
        mBinding.createAccount.setOnClickListener{
            val inputEmail = mBinding.inputEmail.text.toString()
            val inputPassword = mBinding.inputPassword.text.toString()
            if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                EMAIL = inputEmail
                PASSWORD = inputPassword
                mViewModel.initDatabase(SIGNUP) {
                    APP_ACTIVITY.navController.navigate(R.id.action_signInFragment_to_main)
                }
            }else{
                showToast("Введите логин и пороль")
            }
        }
    }


}