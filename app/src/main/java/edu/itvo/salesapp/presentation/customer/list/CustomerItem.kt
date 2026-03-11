package edu.itvo.salesapp.presentation.customer.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.itvo.salesapp.domain.model.Customer
@Composable
fun CustomerItem (customer: Customer) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "ID: ${customer.id}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = customer.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = customer.email,
                style = MaterialTheme.typography.labelSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}