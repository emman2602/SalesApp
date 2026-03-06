package edu.itvo.salesapp.data.repository

import edu.itvo.salesapp.domain.model.Product
import edu.itvo.salesapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryProductRepository @Inject constructor()
    : BaseInMemoryRepository<Product, String>(
    initialData = listOf(
        Product("P1", "Laptop", "Electronics","Electronics" ,15000.0, 10, true),
        Product("P2", "Mouse", "Electronics", "Electronics",500.0,10,  true),
        Product("P3", "Desk", "Furniture","Furniture", 3000.0, 5, false)
    )
),
    ProductRepository {

    override fun getId(item: Product): String = item.code

    override fun observeAll(): Flow<List<Product>> = state

    override suspend fun saveProduct(product: Product) {
        save(product)
    }

    override suspend fun deleteProduct(productCode: String) {
        deleteById(productCode)
    }

    override suspend fun findProductByCode(productCode: String): Product? {
        return findById(productCode)
    }

    override fun getProducts(): Flow<List<Product>> = observeAll()
}