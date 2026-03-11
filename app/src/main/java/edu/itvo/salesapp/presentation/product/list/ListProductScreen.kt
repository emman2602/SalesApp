package edu.itvo.salesapp.presentation.product.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun ListProductScreen(
    onNavigateToCreate: () -> Unit,
    viewModel: ListProductViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ){ paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)){
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.products.isEmpty() -> {
                    EmptyProductsView()
                }

                else -> {
                    ListProduct(products = uiState.products)

                }
            }
        }


    }

}
