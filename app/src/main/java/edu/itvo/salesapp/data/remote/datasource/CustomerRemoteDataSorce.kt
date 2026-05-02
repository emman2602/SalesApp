package edu.itvo.salesapp.data.remote.datasource

import edu.itvo.salesapp.data.remote.api.CustomerApiService
import edu.itvo.salesapp.data.remote.dto.CustomerDTO
import edu.itvo.salesapp.data.remote.dto.ProductDTO
import edu.itvo.salesapp.data.remote.dto.ProductResponse
import javax.inject.Inject

class CustomerRemoteDataSource @Inject constructor(
    private val apiService: CustomerApiService
) {
    suspend fun findCustomerById(id: String): CustomerDTO{
        return apiService.findCustomerById(id);
    }

    suspend fun saveCustomer(customer: CustomerDTO): CustomerDTO{
        return apiService.saveCustomer(customer)
    }

    suspend fun getCustomers(): List<CustomerDTO>{
        return apiService.getCustomers()
    }



}