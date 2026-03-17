package edu.itvo.salesapp.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.itvo.salesapp.presentation.product.create.CreateProductScreen
import edu.itvo.salesapp.presentation.product.list.ListProductScreen


sealed class BottomNavItem(val route: String,val title: String ,val icon: ImageVector){
    object Products: BottomNavItem("product_list", "Productos", Icons.Default.ShoppingCart)
    object Customer: BottomNavItem("customer_list", "Customer", Icons.Default.Person)
}
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "product_list"
    ) {

        composable("product_list") {
            ListProductScreen(
                onNavigateToCreate = {
                    navController.navigate("create_product")
                }
            )
        }

        composable("create_product") {
            CreateProductScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
