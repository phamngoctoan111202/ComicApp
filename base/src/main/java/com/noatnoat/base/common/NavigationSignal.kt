package com.noatnoat.base.common

import androidx.lifecycle.MutableLiveData

object NavigationSignal {
    val currentFragment = MutableLiveData<String>("")
}
