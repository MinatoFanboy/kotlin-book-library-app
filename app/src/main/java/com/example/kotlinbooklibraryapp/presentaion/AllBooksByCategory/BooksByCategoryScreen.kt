package com.example.kotlinbooklibraryapp.presentation.AllBooksByCategory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kotlinbooklibraryapp.presentation.CustomViewModel
import com.example.kotlinbooklibraryapp.presentation.UIcomponent.Bookcart
import com.example.kotlinbooklibraryapp.presentation.UIcomponent.Icon
import com.example.kotlinbooklibraryapp.presentation.Effects.AnimateShimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksByCategoryScreen(
    category: String, 
    navHostController: NavHostController,
    viewModel: CustomViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.BringAllBooksByCategory(category)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = category) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon("\uE946")
                    }
                }
            )
        }
    ) { innerPadding ->
        val res = viewModel.state.value
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
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
                    Text(text = res.error)
                }
                res.items.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(res.items) {
                            Bookcart(
                                imageUrl = it.image,
                                title = it.bookName,
                                description = it.bookDescription,
                                navHostController = navHostController,
                                bookUrl = it.bookUrl
                            )
                        }
                    }
                } else -> {
                    Text(text = "No Books Available")
                }
            }
        }
    }
}