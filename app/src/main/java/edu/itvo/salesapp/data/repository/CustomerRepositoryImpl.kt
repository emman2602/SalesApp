package edu.itvo.salesapp.data.repository

import android.util.Log
import edu.itvo.salesapp.data.local.datasource.CustomerLocalDataSource
import edu.itvo.salesapp.data.mapper.toDomain
import edu.itvo.salesapp.data.mapper.toEntity
import edu.itvo.salesapp.data.remote.datasource.CustomerRemoteDataSource
import edu.itvo.salesapp.data.remote.mapper.CustomerRemoteMapper.toDomain
import edu.itvo.salesapp.data.remote.mapper.CustomerRemoteMapper.toDto
import edu.itvo.salesapp.data.remote.mapper.CustomerRemoteMapper.toEntity
import edu.itvo.salesapp.domain.model.Customer
import edu.itvo.salesapp.domain.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.collections.map

class CustomerRepositoryImpl @Inject constructor(
    private val remote: CustomerRemoteDataSource,
    private val local: CustomerLocalDataSource
): CustomerRepository {
    override suspend fun saveCustomer(customer: Customer) {
        try {
            remote.saveCustomer(customer.toDto())
            local.saveCustomer(customer.toEntity())
        } catch (e: Exception) {
            local.saveCustomer(customer.toEntity())
        }
    }

    override suspend fun findCustomerById(idCustomer: String): Customer? {
        val localCustomer = local.findCustomerById(idCustomer)
        if (localCustomer != null) {
            return localCustomer.toDomain()
        }

        // 2. Si no existe, buscar remoto
        return try {
            val remoteCustomer = remote.findCustomerById(idCustomer)

            // guardar en local
            local.saveCustomer(remoteCustomer.toEntity())

            remoteCustomer.toDomain()

        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteCustomer(idCustomer: String) {
        local.deleteCustomer(idCustomer)
    }

    override  fun getCustomers(): Flow<List<Customer>> = flow {
        try {
            val customers = remote.getCustomers()
            local.saveCustomers(customers.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e("Customer Repository","Error al sincronizar ${e.message}")
        }

        emitAll(
            local.getCustomers().map { entities ->
                entities.map { it.toDomain() }
            }
        )
    }.flowOn(Dispatchers.IO)
}

