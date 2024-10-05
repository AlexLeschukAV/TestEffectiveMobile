package com.example.common

import androidx.lifecycle.MutableLiveData
import com.example.common.utils.NavigationData

class BottomNavigationViewSource : MutableLiveData<NavigationData>() {
    companion object {
        val instance = BottomNavigationViewSource()
    }
}