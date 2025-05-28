package com.example.kotlinbooklibraryapp.domain.repo

import com.example.kotlinbooklibraryapp.common.BookCategoryModel
import com.example.kotlinbooklibraryapp.common.BookModel
import com.example.kotlinbooklibraryapp.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepo {
    fun getAllBooks(): Flow<ResultState<List<BookModel>>>
    fun getAllCategories(): Flow<ResultState<List<BookCategoryModel>>>
    fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>>
}