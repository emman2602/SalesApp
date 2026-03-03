package edu.itvo.salesapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import edu.itvo.salesapp.data.repository.InMemoryProductRepository
import edu.itvo.salesapp.domain.repository.ProductRepository
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: InMemoryProductRepository
    ): ProductRepository

}