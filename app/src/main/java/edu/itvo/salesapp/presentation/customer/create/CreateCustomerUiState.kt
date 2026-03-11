package edu.itvo.salesapp.presentation.customer.create

data class CreateCustomerUiState(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val isLoading: Boolean = false
)