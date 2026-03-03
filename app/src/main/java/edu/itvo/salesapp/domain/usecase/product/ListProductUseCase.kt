package edu.itvo.sales.domain.usecase.product


import edu.itvo.salesapp.domain.model.Product
import edu.itvo.salesapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
    }
}