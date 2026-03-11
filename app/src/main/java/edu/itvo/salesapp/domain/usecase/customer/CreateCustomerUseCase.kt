package edu.itvo.salesapp.domain.usecase.customer

import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.repository.CustomerRepository
import javax.inject.Inject

class CreateCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke (customer: Customer){
        val existing = repository.findCustomerById(customer.id)
        require(existing==null){
            "Customer ${customer.name} with ${customer.id} already exists"
        }
        repository.saveCustomer(customer)
    }
}