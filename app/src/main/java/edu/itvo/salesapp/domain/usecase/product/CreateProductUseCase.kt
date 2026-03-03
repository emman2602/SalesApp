package edu.itvo.salesapp.domain.usecase.product

import edu.itvo.salesapp.domain.model.Product
import edu.itvo.salesapp.domain.repository.ProductRepository
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product){
        val existing = repository.findProductByCode(product.code)
        require(existing==null){
            "Product ${product.name} with ${product.code} already exists"
        }
        repository.saveProduct(product)
    }
}