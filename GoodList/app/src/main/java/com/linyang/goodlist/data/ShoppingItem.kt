package com.linyang.goodlist.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShoppingItem {
    var name: String by mutableStateOf("")
    var completed: Boolean by mutableStateOf(false)
}