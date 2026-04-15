package edu.itvo.salesapp.data.remote.dto

data class ProductDTO(
    val code: String,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val taxable: Boolean
)
