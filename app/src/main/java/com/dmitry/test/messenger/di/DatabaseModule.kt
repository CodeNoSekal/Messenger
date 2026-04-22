package com.dmitry.test.messenger.di

import com.dmitry.test.messenger.data.remote.AuthRemoteDataSource
import com.dmitry.test.messenger.data.remote.DatabaseRemoteDataStore
import com.dmitry.test.messenger.data.repository.AuthRepositoryImpl
import com.dmitry.test.messenger.data.repository.UserProfileRepositoryImpl
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.UserProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideFirestore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideUserProfileRepository(
        remote: DatabaseRemoteDataStore
    ): UserProfileRepository {
        return UserProfileRepositoryImpl(remote)
    }
}