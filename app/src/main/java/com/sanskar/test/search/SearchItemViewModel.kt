package com.sanskar.test.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchItemViewModel(searchListItem: SearchListItem): ViewModel() {

    val language = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val startCount = MutableLiveData<Int>()
    val name = MutableLiveData<String>()

    init {
        language.value = searchListItem.desc
        name.value = searchListItem.head
        imageUrl.value = searchListItem.imageUrl
        startCount.value = searchListItem.rating
    }
}