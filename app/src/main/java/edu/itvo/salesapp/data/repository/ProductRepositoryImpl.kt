package edu.itvo.salesapp.data.repository

import android.util.Log
import edu.itvo.salesapp.data.local.datasource.ProductLocalDataSource
import edu.itvo.salesapp.data.mapper.toDomain
import edu.itvo.salesapp.data.remote.datasource.ProductRemoteDataSource
import edu.itvo.salesapp.domain.repository.ProductRepository
import edu.itvo.salesapp.data.mapper.toEntity
import edu.itvo.salesapp.data.remote.mapper.ProductRemoteMapper.toDomain
import edu.itvo.salesapp.data.remote.mapper.ProductRemoteMapper.toDto
import edu.itvo.salesapp.data.remote.mapper.ProductRemoteMapper.toEntity
import edu.itvo.salesapp.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val remote: ProductRemoteDataSource,
    private val local: ProductLocalDataSource
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> = flow {
        // 1. Intentar actualizar desde la red
        try {
            val response = remote.getProducts()

            if (response.success) {
                val remoteProducts = response.data // Extraemos la lista real
                local.saveProducts(remoteProducts.map { it.toEntity() })
            }
        } catch (e: Exception) {
            Log.e("ProductRepository", "Error al sincronizar: ${e.message}")
        }

        // 2. Conectar al flujo de la base de datos (Fuente de Verdad)
        emitAll(
            local.getProducts().map { entities ->
                entities.map { it.toDomain() }
            }
        )
    }.flowOn(Dispatchers.IO)

    override suspend fun findProductByCode(productCode: String): Product? {

        // 1. Buscar local primero
        val localProduct = local.findProductByCode(productCode)
        if (localProduct != null) {
            return localProduct.toDomain()
        }

        // 2. Si no existe, buscar remoto
        return try {
            val remoteProduct = remote.findProductByCode(productCode)

            // guardar en local
            local.saveProduct(remoteProduct.toEntity())

            remoteProduct.toDomain()

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveProduct(product: Product) {
        try {
            remote.saveProduct(product.toDto())
            local.saveProduct(product.toEntity())
        } catch (e: Exception) {
            local.saveProduct(product.toEntity())
        }
    }

    override suspend fun deleteProduct(productCode: String) {
        local.deleteProduct(productCode)
    }
}