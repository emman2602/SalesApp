package edu.itvo.salesapp.domain.model

data class Product(
    val code: String,
    val name: String,
    val description: String,
    val category: String,
    val price: String,
    val stock: String,
    val taxable: Boolean = true
)
