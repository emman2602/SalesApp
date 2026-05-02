package edu.itvo.salesapp.data.remote.dto

data class ProductResponse(
    val success: Boolean,
    val data: List<ProductDTO>
)
