package com.noatnoat.base.presentation.nav

import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController

class NavManager {
    private var navEventListener: ((navDirections: NavDirections) -> Unit)? = null
    private var backEventListener: (() -> Unit)? = null

    fun navigate(navDirections: NavDirections) {
        navEventListener?.invoke(navDirections)
    }

    fun back() {
        backEventListener?.invoke()
    }

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navEventListener = navEventListener


    }

}


