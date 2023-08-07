package com.example.view.fifa.util.idmodule

import com.example.view.fifa.network.managers.FIFAManager
import com.example.view.fifa.network.managers.FIFAManagerImpl
import com.example.view.fifa.network.managers.FIFAMetadataManager
import com.example.view.fifa.network.managers.FIFAMetadataManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun bindSearchManager(searchManagerImpl: FIFAManagerImpl): FIFAManager

    @Binds
    @Singleton
    abstract fun bindSearchMetadataManager(searchMetadataManagerImpl: FIFAMetadataManagerImpl): FIFAMetadataManager


}