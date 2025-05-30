package com.example.kotlinbooklibraryapp.presentation.AllBooksScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kotlinbooklibraryapp.presentation.CustomViewModel
import com.example.kotlinbooklibraryapp.presentation.Effects.AnimateShimmer
import com.example.kotlinbooklibraryapp.presentation.UIcomponent.Bookcart

@Composable
fun AllBooksScreen(
    navHostController: NavHostController, 
    modifier: Modifier = Modifier,
    viewModel: CustomViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.BringAllBooks()
    }

    val res = viewModel.state.value

    when {
        res.isLoading -> {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    items(10) {
                        AnimateShimmer()
                    }
                }
            }
        }
        res.error.isNotEmpty() -> {
            Text(text = res.error, modifier = modifier)
        }
        res.items.isNotEmpty() -> {
            Column(modifier = modifier.fillMaxSize()) {
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    items(res.items) {
                        Bookcart(
                            author = it.bookAuthor,
                            imageUrl = it.image,
                            title = it.bookName,
                            description = it.bookDescription,
                            navHostController = navHostController,
                            bookUrl = it.bookUrl
                        )
                    }
                }
            }
        } else -> Text(text = "No books Available", modifier = modifier)
    }
}