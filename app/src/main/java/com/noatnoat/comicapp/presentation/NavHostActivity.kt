package com.noatnoat.comicapp.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.noatnoat.base.presentation.activity.BaseActivity
import com.noatnoat.base.presentation.ext.navigateSafe
import com.noatnoat.base.presentation.nav.NavManager
import com.noatnoat.comicapp.R
import com.noatnoat.comicapp.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity(R.layout.activity_nav_host), NavController.OnDestinationChangedListener {

    private val binding: ActivityNavHostBinding by viewBinding()
    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAppBar()
        initNavManager()
        initBottomNavigation()
    }

    private fun initAppBar() {
        binding.apply {
            mainAppToolbar = binding.mainToolbar
            appBarLayout = binding.mainAppbarLayout
            searchTextInputEditText = binding.mainSearchTextInputEditText
            searchLayout = binding.mainSearchLayout
            searchTextInputLayout = binding.mainSearchTextInputLayout
        }
    }

    private fun initBottomNavigation() {
        // When using FragmentContainerView, NavController fragment can't be retrieved by using `findNavController()`
        //
        // See https://stackoverflow.com/questions/59275009/fragmentcontainerview-using-findnavcontroller/59275182#59275182
        // See https://issuetracker.google.com/issues/142847973
//        val navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
//        navController.addOnDestinationChangedListener(this)
//        binding.bottomNav.setupWithNavController(navController)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
            currentFragment?.navigateSafe(it)
        }
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {

    }
}
