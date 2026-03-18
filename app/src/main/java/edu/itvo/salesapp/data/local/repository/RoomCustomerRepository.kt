package edu.itvo.salesapp.data.local.repository

import edu.itvo.salesapp.data.local.dao.CustomerDao
import edu.itvo.salesapp.data.mapper.toDomain
import edu.itvo.salesapp.data.mapper.toEntity
import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCustomerRepository @Inject constructor(
    private val dao: CustomerDao
): CustomerRepository {
    override suspend fun saveCustomer(customer: Customer) {
        dao.insert(customer.toEntity())
    }

    override suspend fun findCustomerById(idCustomer: String): Customer? {
        return dao.findById(idCustomer)?.toDomain()
    }

    override suspend fun deleteCustomer(idCustomer: String) {
        dao.deleteById(idCustomer)
    }

    override  fun getCustomers(): Flow<List<Customer>> {
        return dao.getCustomers()
            .map { list -> list.map { it.toDomain() } }
    }
}