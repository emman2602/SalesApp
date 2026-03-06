package edu.itvo.salesapp.domain.validation


import edu.itvo.salesapp.domain.model.Product
import edu.itvo.salesapp.presentation.product.ValidationResult

class ProductValidator {

    operator fun invoke(product: Product): ValidationResult =
        listOfNotNull(
            "Code required".takeIf { product.code.isBlank() },
            "Description required".takeIf { product.description.isBlank() },
            "Category required".takeIf { product.category.isBlank() },
            "Invalid price".takeIf { product.price.toDouble() < 0 },
            "Invalid Stock".takeIf { product.stock.toInt() <= 0 }
        ).firstOrNull()
            ?.let { ValidationResult.Error(it) }
            ?: ValidationResult.Success
}