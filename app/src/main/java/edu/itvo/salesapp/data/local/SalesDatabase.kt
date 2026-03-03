package edu.itvo.salesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.vo.Database
import edu.itvo.sales.data.local.dao.ProductDao
import edu.itvo.sales.data.local.entity.ProductEntity
import edu.itvo.salesapp.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SalesDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}