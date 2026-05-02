package edu.itvo.salesapp.data.repository

import edu.itvo.salesapp.data.remote.datasource.CustomerFirebaseDataSource
import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreCustomerRepository @Inject constructor(
    private val firebaseDataSource: CustomerFirebaseDataSource
) : CustomerRepository {


    override suspend fun saveCustomer(customer: Customer) {
        firebaseDataSource.saveCustomer(customer)
    }

    override suspend fun findCustomerById(idCustomer: String): Customer? {
        return firebaseDataSource.findCustomerById(idCustomer)
    }

    override suspend fun deleteCustomer(idCustomer: String) {
        firebaseDataSource.deleteCustomer(idCustomer)
    }

    override  fun getCustomers(): Flow<List<Customer>> {
        return firebaseDataSource.getCustomers()
    }
}