package com.dmitry.test.messenger.di

import com.dmitry.test.messenger.data.remote.AuthRemoteDataSource
import com.dmitry.test.messenger.data.repository.AuthRepositoryImpl
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.UserProfileRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideAuthRemoteDataSource(
        auth: FirebaseAuth
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(auth)
    }

    @Provides
    fun provideAuthRepository(
        remote: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(remote)
    }
}