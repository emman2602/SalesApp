package edu.itvo.salesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.itvo.salesapp.data.local.entity.CustomerEntity
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
}