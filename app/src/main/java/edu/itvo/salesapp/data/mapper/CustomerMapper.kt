package edu.itvo.salesapp.data.mapper

import edu.itvo.salesapp.data.local.entity.CustomerEntity
import edu.itvo.salesapp.data.local.entity.ProductEntity
import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.model.Product

fun CustomerEntity.toDomain(): Customer{
    return Customer(
        id,
        name,
        email
    )
}

fun Customer.toEntity(): CustomerEntity{
    return CustomerEntity(
        id,
        name,
        email
    )
}