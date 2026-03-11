package edu.itvo.salesapp.domain.usecase.customer

import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.model.Product
import edu.itvo.salesapp.domain.repository.CustomerRepository
import edu.itvo.salesapp.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
){
     operator fun invoke(): Flow<List<Customer>>{
        return repository.getCustomers()
    }
}

