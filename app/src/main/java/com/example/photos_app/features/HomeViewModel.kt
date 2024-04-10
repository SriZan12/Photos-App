package com.example.photos_app.features

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.photos_app.events.ImageEvent
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    var size = MutableStateFlow("")
    var imageList = MutableStateFlow<List<Uri>?>(null)
    var indexes = MutableStateFlow<Set<Int>?>(null)
    var isImageGenerated = MutableStateFlow(false)

    fun onEvent(event: ImageEvent) {
        when (event) {
            is ImageEvent.OnPickImage -> handleImagePick(uris = event.uris, size = size.value.toIntOrNull() ?: 0)
            is ImageEvent.OnSizeChange -> handleSizeChange(event.size)
            is ImageEvent.OnClearSize -> handleClearSize()
        }
    }

    // Handle image pick event
    private fun handleImagePick(uris: List<Uri>, size: Int) {
        val list = mutableListOf<Uri>()
        val indices = mutableSetOf<Int>()
        for (i in 1..size) {
            val ind = i * (i + 1) / 2
            if (i < size) {
                indices.add(ind)
            }
        }
        indexes.value = indices
        for (index in 0 until size) {
            list.add(if (index in indices) uris[0] else uris[1])
        }
        imageList.value = list
    }

    // Handle size change event
    private fun handleSizeChange(newSize: String) {
        size.value = newSize
    }

    // Handle clear size event
    private fun handleClearSize() {
        size.value = ""
        indexes.value = null
        imageList.value = null
    }
}
