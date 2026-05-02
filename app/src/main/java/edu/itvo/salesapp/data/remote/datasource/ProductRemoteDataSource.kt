package edu.itvo.salesapp.data.remote.datasource

import edu.itvo.salesapp.data.remote.api.ProductApiService
import edu.itvo.salesapp.data.remote.dto.ProductDTO
import edu.itvo.salesapp.data.remote.dto.ProductResponse
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApiService
) {

    suspend fun findProductByCode(code: String): ProductDTO {
        return api.findProductByCode(code)
    }

    suspend fun saveProduct(product: ProductDTO): ProductDTO {
        return api.saveProduct(product)
    }

    suspend fun getProducts(): ProductResponse {
        return api.getProducts()
    }
}