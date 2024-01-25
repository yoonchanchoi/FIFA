package com.football.view.fifa.util.idmodule

import com.football.view.fifa.network.managers.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindSearchMetadataManager(searchMetadataManagerImpl: FIFAMetadataManagerImpl): FIFAMetadataManager

    @Binds
    @Singleton
    abstract fun bindSearchManager(searchManagerImpl: FIFAManagerImpl): FIFAManager

}