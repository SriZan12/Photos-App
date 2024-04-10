package com.example.photos_app.events

import android.net.Uri

sealed class ImageEvent {
    data class OnPickImage(val uris: List<Uri>): ImageEvent()
    data class OnSizeChange(val size: String): ImageEvent()
    data object OnClearSize: ImageEvent()
}