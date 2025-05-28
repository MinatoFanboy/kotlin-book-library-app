package com.example.kotlinbooklibraryapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinbooklibraryapp.common.BookCategoryModel
import com.example.kotlinbooklibraryapp.common.BookModel
import com.example.kotlinbooklibraryapp.common.ResultState
import com.example.kotlinbooklibraryapp.domain.repo.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(val repo: AllBookRepo): ViewModel() {
    private val _state = mutableStateOf(ItemState())
    val state: State<ItemState> = _state

    fun BringAllBooks() {
        viewModelScope.launch {
            repo.getAllBooks().collect{
                when(it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }
                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

    fun BringCategories() {
        viewModelScope.launch {
            repo.getAllCategories().collect{
                when(it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _state.value = ItemState(category = it.data)
                    }
                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

    fun BringAllBooksByCategory(category: String) {
        viewModelScope.launch {
            repo.getAllBooksByCategory(category).collect{
                when(it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }
                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }
}

data class ItemState(
    val isLoading: Boolean = false,
    val items: List<BookModel> = emptyList(),
    val error: String = "",
    val category: List<BookCategoryModel> = emptyList()
)
