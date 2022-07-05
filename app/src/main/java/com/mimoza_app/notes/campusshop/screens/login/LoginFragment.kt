package com.mimoza_app.notes.campusshop.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentLoginBinding
import com.mimoza_app.notes.campusshop.util.*


class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var preferenceManager:PreferenceManager
    private lateinit var mViewModel:LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        preferenceManager = PreferenceManager()
        preferenceManager.PreferenceManager(APP_ACTIVITY)
        mViewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]
        mBinding.signUpBtn.setOnClickListener{
            APP_ACTIVITY.navController.navigate(R.id.action_loginFragment_to_signInFragment)
        }
        mBinding.signInBtn.setOnClickListener{
            login()
        }
        mBinding.forgotPassword.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_loginFragment_to_forgotPassword2)
        }
    }

    fun login(){
        val email = mBinding.inputEmail.text.toString()
        val pass = mBinding.inputPassword.text.toString()
        if(isValidLogin(email,pass)){
            mViewModel.login(email,pass,preferenceManager)
        }
    }

    private fun isValidLogin(email:String,pass:String):Boolean{
        if(email.isNotEmpty() && pass.isNotEmpty()){
            return true
        }else{
            showToast("Некорректный ввод данных")
            return false
        }
    }

}