package edu.itvo.salesapp.data.mapper

import edu.itvo.salesapp.data.local.entity.ProductEntity
import edu.itvo.salesapp.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        code,
        name,
        description,
        category,
        price,
        stock,
        taxable
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        code,
        name,
        description,
        category,
        price,
        stock,
        taxable
    )
}