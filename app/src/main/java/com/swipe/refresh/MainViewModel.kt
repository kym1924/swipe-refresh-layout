package com.swipe.refresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _swipeCount = MutableStateFlow(0)
    val swipeCount = _swipeCount.asStateFlow()

    fun upCount() {
        viewModelScope.launch {
            _swipeCount.emit(_swipeCount.value + 1)
        }
    }
}
