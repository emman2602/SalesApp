package edu.itvo.salesapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.itvo.salesapp.presentation.customer.create.CreateCustomerScreen
import edu.itvo.salesapp.presentation.customer.list.ListCustomerScreen
import edu.itvo.salesapp.presentation.product.create.CreateProductScreen
import edu.itvo.salesapp.presentation.product.list.ListProductScreen


sealed class BottomNavItem(val route: String,val title: String ,val icon: ImageVector){
    object Products: BottomNavItem("product_list", "Products", Icons.Default.ShoppingCart)
    object Customer: BottomNavItem("customer_list", "Customer", Icons.Default.Person)
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.Products,
        BottomNavItem.Customer
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                // Evita acumular múltiples copias de una misma pantalla
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Products.route,
            modifier = Modifier.padding(innerPadding) // Evita que la lista se oculte debajo de la barra
        ) {
            // --- Rutas de Productos ---
            composable("product_list") {
                ListProductScreen(
                    onNavigateToCreate = { navController.navigate("create_product") }
                )
            }
            composable("create_product") {
                CreateProductScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // --- Rutas de Clientes ---
            composable("customer_list") {
                ListCustomerScreen(
                    onNavigateToCreate = { navController.navigate("create_customer") }
                )
            }
            composable("create_customer") {
                CreateCustomerScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
