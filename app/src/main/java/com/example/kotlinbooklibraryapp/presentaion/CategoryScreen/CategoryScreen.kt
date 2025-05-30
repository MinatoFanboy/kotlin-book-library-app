package com.example.kotlinbooklibraryapp.presentation.CategoryScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import com.example.kotlinbooklibraryapp.presentation.Effects.categoryShimmer
import com.example.kotlinbooklibraryapp.presentation.CustomViewModel
import com.example.kotlinbooklibraryapp.presentation.UIcomponent.BookCategoryCard

@Composable
fun CategoryScreen(navHostController: NavHostController, viewModel: CustomViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.BringCategories()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val res = viewModel.state.value
        when {
            res.isLoading -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        GridCells.Fixed(2), 
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(10) {
                            categoryShimmer()
                        }
                    }
                }
            }

            res.error.isNotEmpty() -> {
                Text(text = res.error)
            }

            res.category.isNotEmpty() -> {
                LazyVerticalGrid(
                    GridCells.Fixed(2), 
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(res.category) { category ->
                        BookCategoryCard(
                            imageUrl = category.categoryImageUrl,
                            category = category.name,
                            navHostController = navHostController
                        )
                    }
                }
            }
        }
    }
}