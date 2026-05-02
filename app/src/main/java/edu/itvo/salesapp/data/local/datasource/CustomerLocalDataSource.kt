package edu.itvo.salesapp.data.local.datasource

import edu.itvo.salesapp.data.local.dao.CustomerDao
import edu.itvo.salesapp.data.local.entity.CustomerEntity
import edu.itvo.salesapp.data.local.entity.ProductEntity
import edu.itvo.salesapp.domain.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerLocalDataSource @Inject constructor(
    private val dao: CustomerDao
) {
    fun getCustomers(): Flow<List<CustomerEntity>>{
        return dao.getCustomers()
    }
    suspend fun findCustomerById(id: String): CustomerEntity?{
        return dao.findById(id)
    }

    suspend fun saveCustomer(customer: CustomerEntity){
        dao.insert(customer)
    }

    suspend fun deleteCustomer(id: String){
        dao.deleteById(id)
    }

    suspend fun saveCustomers(customers: List<CustomerEntity>){
        dao.insertAll(customers)
    }

}

