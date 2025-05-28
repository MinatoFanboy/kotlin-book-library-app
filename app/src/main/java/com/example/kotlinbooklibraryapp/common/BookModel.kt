package com.example.kotlinbooklibraryapp.common

data class BookModel(
    val bookName: String = "",
    val bookAuthor: String = "",
    val bookUrl: String = "",
    val bookDescription: String = "",
    val category: String = "",
    val image: String = ""
)
