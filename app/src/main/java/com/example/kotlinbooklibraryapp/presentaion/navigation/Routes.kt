package com.example.kotlinbooklibraryapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object HomeScreen

    @Serializable
    object data class BooksByCategory(val category: String)

    @Serializable
    data class ShowPdfScreen(val url: String)
}