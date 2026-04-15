package edu.itvo.salesapp.data.repository

import edu.itvo.salesapp.data.local.datasource.ProductLocalDataSource
import edu.itvo.salesapp.data.mapper.toDomain
import edu.itvo.salesapp.data.remote.datasource.ProductRemoteDataSource
import edu.itvo.salesapp.domain.repository.ProductRepository
import edu.itvo.salesapp.data.mapper.toEntity
import edu.itvo.salesapp.data.remote.mapper.ProductRemoteMapper.toDomain
import edu.itvo.salesapp.data.remote.mapper.ProductRemoteMapper.toDto
import edu.itvo.salesapp.data.remote.mapper.ProductRemoteMapper.toEntity
import edu.itvo.salesapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val remote: ProductRemoteDataSource,
    private val local: ProductLocalDataSource
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> = flow {

        //--- Emitir datos locales inmediatamente (UI rápida)
        emitAll(
            local.getProducts()
                .map { list -> list.map { it.toDomain() } }
        )

        //--- Intentar actualizar desde la API
        try {
            val remoteProducts = remote.getProducts()

            //---  Guardar en Room (esto dispara automáticamente el Flow)
            local.saveProducts(remoteProducts.map { it.toEntity()})

        } catch (e: Exception) {
            e.printStackTrace()
            //--- aquí se puede manejar error (log, retry, etc.)
        }
    }

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