package com.sanskar.test.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var searchClick = MutableLiveData<String>()

    fun onSearchClick(searchKeyword: String) {
        searchClick.value = searchKeyword
    }
}