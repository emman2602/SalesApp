package edu.itvo.salesapp.data.remote.mapper


import edu.itvo.salesapp.data.local.entity.CustomerEntity
import edu.itvo.salesapp.data.remote.dto.CustomerDTO

import edu.itvo.salesapp.domain.model.Customer


object CustomerRemoteMapper {
    fun CustomerDTO.toDomain(): Customer = Customer(
        id, name, email
    )

    fun CustomerDTO.toEntity(): CustomerEntity = CustomerEntity(
        id, name, email
    )

    fun Customer.toDto(): CustomerDTO = CustomerDTO (
        id, name, email
    )
}

