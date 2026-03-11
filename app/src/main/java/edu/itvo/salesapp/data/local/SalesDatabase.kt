package edu.itvo.salesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.itvo.salesapp.data.local.dao.CustomerDao
import edu.itvo.salesapp.data.local.dao.ProductDao
import edu.itvo.salesapp.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SalesDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao

}