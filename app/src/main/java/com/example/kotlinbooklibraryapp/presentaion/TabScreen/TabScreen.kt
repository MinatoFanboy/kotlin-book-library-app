package com.example.kotlinbooklibraryapp.presentation.TabScreen

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kotlinbooklibraryapp.presentation.AllBooksScreen.AllBooksScreen
import com.example.kotlinbooklibraryapp.presentation.CategoryScreen.CategoryScreen
import com.example.kotlinbooklibraryapp.presentation.UIcomponent.Icon
import kotlinx.coroutines.launch

data class TabItem(val title: String, val icon: String)

@Composable
fun TabScreen(navHostController: NavHostController) {
    val tabs = listOf(
        TabItem("Category", "\uE9CF"),
        TabItem("Books", "\uEA01")
    )
    
    val paperState = rememberPagerState(pageCount = {tabs.size})
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = paperState.currentPage, modifier = Modifier.fillMaxWidth()) {
            tabs.forEachIndexed{ index, tab -> 
                Tab(
                    modifier = Modifier.fillMaxWidth(),
                    selected = paperState.currentPage == index,
                    onClick = {
                        scope.launch {
                            paperState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(tab.icon)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(tab.title)
                        }
                    }
                )
            }
        }
        HorizontalPager(state = paperState) { page -> 
            when(page) {
                0 -> CategoryScreen(navHostController = navHostController)
                1 -> AllBooksScreen(navHostController = navHostController)
            }
        }
    }
}