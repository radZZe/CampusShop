package com.mimoza_app.notes.campusshop.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.internal.ContextUtils.getActivity
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.ActivityMainBinding
import com.mimoza_app.notes.campusshop.screens.login.LoginFragment
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY
import com.mimoza_app.notes.campusshop.util.showToast

class MainActivity : AppCompatActivity() { //jopa

    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        navController = Navigation.findNavController(this,R.id.nav_host)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host);
        if (currentFragment != null) {
            if (currentFragment.equals(LoginFragment())) {
                Log.v("jopa","Работает")
            }else{
                Log.v("jopa","Не работает")
            }
        }

    }
}