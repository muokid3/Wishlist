package com.dm.berxley.wishlist.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WishViewModel: ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newTitle: String){
        wishTitleState = newTitle
    }

    fun onWishDescriptionChanged(newDesc: String){
        wishDescriptionState = newDesc
    }
}