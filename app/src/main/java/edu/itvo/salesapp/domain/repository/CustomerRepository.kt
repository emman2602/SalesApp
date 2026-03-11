package edu.itvo.salesapp.domain.repository

import edu.itvo.salesapp.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    suspend fun saveCustomer(customer: Customer)
    suspend fun findCustomerById(idCustomer: String): Customer?
    suspend fun deleteCustomer(idCustomer: String)

    fun getCustomers(): Flow<List<Customer>>
}