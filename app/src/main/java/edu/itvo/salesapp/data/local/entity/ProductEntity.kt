package edu.itvo.salesapp.data.local.entity

import androidx.room.vo.Entity


@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey
    val code: String,

    val description: String,

    val category: String,

    val price: Double,

    val stock: Int,

    val taxable: Boolean
)


