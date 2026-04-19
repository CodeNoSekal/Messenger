package com.dmitry.test.messenger.data.database.remote

import com.dmitry.test.messenger.data.database.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RemoteDataStore @Inject constructor(
    private val firestore: FirebaseFirestore
){
    fun setProfile(uid: String, profile: UserProfile){
        firestore.collection("users")
            .document(uid)
            .set(profile)
    }

//    fun getProfile(uid: String) : UserProfile? {
//        firestore.collection("users")
//            .document()
//    }
}