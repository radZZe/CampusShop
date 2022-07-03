package com.mimoza_app.notes.campusshop.screens


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mimoza_app.notes.campusshop.R
import com.mimoza_app.notes.campusshop.databinding.ActivityMainBinding
import com.mimoza_app.notes.campusshop.util.APP_ACTIVITY


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

        mBinding.bottomNavMenu.setupWithNavController(navController) // Setup menu navigation

        val navView: BottomNavigationView = mBinding.bottomNavMenu
        NavigationUI.setupWithNavController(navView, navController)


        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination, @Nullable arguments: Bundle?
            ) {
                nav_changed_listener()
            }
        })

    }

     fun nav_changed_listener(){
         val currentFragment = navController.currentDestination
         val fragment_login = navController.findDestination(R.id.loginFragment)
         val fragment_forgot_password = navController.findDestination(R.id.forgotPassword2)
         val fragment_signUp = navController.findDestination(R.id.signInFragment)
         if (currentFragment != null) {
             if(currentFragment == fragment_login || currentFragment == fragment_forgot_password
                 || currentFragment == fragment_signUp)  {
                 mBinding.bottomNavMenu.visibility = View.GONE
             }else{
                 mBinding.bottomNavMenu.visibility = View.VISIBLE
             }
         }
     }

}