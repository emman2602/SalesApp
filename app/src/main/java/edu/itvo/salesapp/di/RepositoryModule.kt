package edu.itvo.salesapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import edu.itvo.salesapp.data.local.repository.RoomCustomerRepository

import edu.itvo.salesapp.data.local.repository.RoomProductRepository
import edu.itvo.salesapp.data.remote.FirestoreProductRepository
import edu.itvo.salesapp.domain.repository.CustomerRepository
import edu.itvo.salesapp.domain.repository.ProductRepository
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: FirestoreProductRepository//RoomProductRepository
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        repository: RoomCustomerRepository
    ): CustomerRepository
}