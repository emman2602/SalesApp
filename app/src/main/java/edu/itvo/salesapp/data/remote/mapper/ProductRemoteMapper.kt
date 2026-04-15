package edu.itvo.salesapp.data.remote.mapper

import edu.itvo.salesapp.data.local.entity.ProductEntity
import edu.itvo.salesapp.data.remote.dto.ProductDTO
import edu.itvo.salesapp.domain.model.Product

object ProductRemoteMapper {
    fun ProductDTO.toDomain(): Product = Product(
        code, name, description, category, price, stock, taxable
    )

    fun ProductDTO.toEntity(): ProductEntity = ProductEntity(
        code,name, description, category, price, stock, taxable
    )

    fun Product.toDto(): ProductDTO = ProductDTO(
        code,name, description, category, price, stock, taxable
    )
}