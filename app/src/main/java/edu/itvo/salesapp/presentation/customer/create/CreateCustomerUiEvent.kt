package edu.itvo.salesapp.presentation.customer.create

import edu.itvo.salesapp.presentation.product.create.CreateProductUiEvent

sealed interface CreateCustomerUiEvent {
    data class IdChanged( val value: String): CreateCustomerUiEvent
    data class NameChanged(val value: String): CreateCustomerUiEvent
    data class EmailChanged( val value: String): CreateCustomerUiEvent

    object SaveClicked: CreateCustomerUiEvent
}