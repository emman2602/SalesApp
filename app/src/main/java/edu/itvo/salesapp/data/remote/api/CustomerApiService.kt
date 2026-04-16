package edu.itvo.salesapp.data.remote.api

import edu.itvo.salesapp.data.remote.dto.CustomerDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerApiService {
    @GET("customers")
    suspend fun getCustomers(): List<CustomerDTO>

    @GET("customers/{id}")
    suspend fun findCustomerById(
        @Path("code") code: String
    ): CustomerDTO

    @POST("customers")
    suspend fun saveCustomer(
        @Body customer: CustomerDTO
    ): CustomerDTO

    @PUT("customers/{id}")
    suspend fun updateCustomer (  @Body customer: CustomerDTO): CustomerDTO

    @DELETE("customers/{id}")
    suspend fun deleteCustomer( @Path("id") id: String)


}