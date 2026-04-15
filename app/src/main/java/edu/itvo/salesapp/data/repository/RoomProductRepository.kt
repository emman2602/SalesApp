package edu.itvo.salesapp.data.repository

import edu.itvo.salesapp.data.local.dao.ProductDao
import edu.itvo.salesapp.data.mapper.toDomain
import edu.itvo.salesapp.data.mapper.toEntity
import edu.itvo.salesapp.domain.model.Product
import edu.itvo.salesapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomProductRepository @Inject constructor(
    private val dao: ProductDao
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun findProductByCode(productCode: String): Product? {
        return dao.findByCode(productCode)?.toDomain()
    }

    override suspend fun saveProduct(product: Product) {
        dao.insert(product.toEntity())
    }

    override suspend fun deleteProduct(productCode: String) {
        dao.deleteByCode(productCode)
    }
}