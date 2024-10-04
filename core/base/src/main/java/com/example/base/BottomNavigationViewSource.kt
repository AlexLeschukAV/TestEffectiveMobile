package com.example.base

import androidx.lifecycle.MutableLiveData
import com.example.base.utils.NavigationData

class BottomNavigationViewSource : MutableLiveData<NavigationData>() {
    companion object {
        val instance = BottomNavigationViewSource()
    }
}