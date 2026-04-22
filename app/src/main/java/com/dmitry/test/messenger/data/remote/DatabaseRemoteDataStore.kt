package com.dmitry.test.messenger.data.remote

import com.dmitry.test.messenger.domain.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseRemoteDataStore @Inject constructor(
    private val firestore: FirebaseFirestore
){
    suspend fun createProfile(uid: String, profile: UserProfile){
        firestore.collection("users")
            .document(uid)
            .set(profile)
            .await()
    }

    fun observeProfile(uid: String): Flow<UserProfile?> = callbackFlow {
        val listener = firestore.collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val profile = snapshot?.toObject(UserProfile::class.java)
                trySend(profile)
            }

        awaitClose {
            listener.remove()
        }
    }

    suspend fun getProfile(uid: String) : UserProfile? {
        val snapshot = firestore.collection("users")
            .document(uid)
            .get()
            .await()

        return snapshot.toObject(UserProfile::class.java)
    }
}