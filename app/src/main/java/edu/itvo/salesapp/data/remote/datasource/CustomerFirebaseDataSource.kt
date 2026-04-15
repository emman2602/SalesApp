package edu.itvo.salesapp.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import edu.itvo.salesapp.domain.model.Customer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CustomerFirebaseDataSource @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("customers")
    fun getCustomers(): Flow<List<Customer>> = callbackFlow {
        val listener = collection.addSnapshotListener { snapshot, error ->

            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val customers = snapshot?.documents?.mapNotNull {
                it.toObject(Customer::class.java)
            } ?: emptyList()
            trySend(customers)
        }
        awaitClose { listener.remove() }
    }

    suspend fun findCustomerById(idCustomer: String): Customer? {
        val doc = collection.document(idCustomer).get().await()
        return doc.toObject(Customer::class.java)
    }

    suspend fun saveCustomer(customer: Customer) {
        collection.document(customer.id).set(customer).await()
    }

    suspend fun deleteCustomer(idCustomer: String) {
        collection.document(idCustomer).delete().await()
    }
}