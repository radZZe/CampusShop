package com.mimoza_app.notes.campusshop.screens.forgot_password

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.FragmentForgotPasswordBinding
import com.mimoza_app.notes.campusshop.databinding.FragmentLoginBinding
import com.mimoza_app.notes.campusshop.screens.login.LoginFragmentViewModel
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY


class ForgotPassword : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: ForgotPasswordFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[ForgotPasswordFragmentViewModel::class.java]
        mBinding.resetPass.setOnClickListener{
            val email = mBinding.emailInput.text.toString()
            mViewModel.resetPassword(email){
                val dialog = AlertDialog.Builder(context)
                    .setTitle("Восстановление пороля")
                    .setMessage("Сообщение на почту было отправлено")
                    .setPositiveButton("Хорошо", DialogInterface.OnClickListener{
                            dialogInterface, _ ->
                        dialogInterface.cancel()
                        APP_ACTIVITY.navController.navigate(R.id.action_forgotPassword2_to_loginFragment)
                    })
                    .create()
                dialog.show()
            }
        }
    }


}