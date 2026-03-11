package edu.itvo.salesapp.presentation.customer.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.presentation.product.list.ProductItem

@Composable
fun ListCustomer(customers: List<Customer>) {
    LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = customers,
            key = { it.id }
        ) { customer ->
            CustomerItem(customer = customer)
        }
    }
}