package com.example.kotlinbooklibraryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.kotlinbooklibraryapp.ui.theme.KotlinBookLibraryTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.kotlinbooklibraryapp.presentation.navigation.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBookLibraryTheme {
                val navHostController = rememberNavController()
                
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(navHostController = navHostController)
                }
            }
        }
    }
}
