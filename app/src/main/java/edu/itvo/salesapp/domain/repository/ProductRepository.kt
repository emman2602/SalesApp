package edu.itvo.salesapp.domain.repository

import edu.itvo.salesapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun saveProduct(product: Product)
    suspend fun findProductByCode(productCode: String): Product?
    suspend fun deleteProduct(productCode: String)
    fun getProducts(): Flow<List<Product>>
}