package edu.itvo.salesapp.data.remote.api


import edu.itvo.salesapp.data.remote.dto.ProductDTO
import edu.itvo.salesapp.data.remote.dto.ProductResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{code}")
    suspend fun findProductByCode(
        @Path("code") code: String
    ): ProductDTO

    @POST("products")
    suspend fun saveProduct(
        @Body product: ProductDTO
    ): ProductDTO

    @PUT("products/{code}")
    suspend fun updateProduct (  @Body product: ProductDTO): ProductDTO

    @DELETE("products/{code}")
    suspend fun deleteProduct ( @Path("code") code: String)

}