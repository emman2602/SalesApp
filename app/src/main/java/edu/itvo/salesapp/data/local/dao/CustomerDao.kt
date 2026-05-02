package edu.itvo.salesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import edu.itvo.salesapp.data.local.entity.CustomerEntity
import edu.itvo.salesapp.data.local.entity.ProductEntity
import edu.itvo.salesapp.domain.model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers")
    fun getCustomers(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE id = :idCustomer")
    suspend fun findById(idCustomer: String): CustomerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: CustomerEntity)

    @Query("DELETE FROM customers WHERE id = :idCustomer")
    suspend fun deleteById(idCustomer: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(customers: List<CustomerEntity>)

    @Query("DELETE FROM customers")
    suspend fun clearAll()

    @Transaction
    suspend fun replaceAll(customers: List<CustomerEntity>) {
        clearAll()
        insertAll(customers)
    }
}