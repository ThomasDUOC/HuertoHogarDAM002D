package com.g001.huertohogar_dam002d.data.di

import android.content.Context
import com.g001.huertohogar_dam002d.data.AppDatabase
import com.g001.huertohogar_dam002d.data.CarritoDao
import com.g001.huertohogar_dam002d.data.CarritoRepository
import com.g001.huertohogar_dam002d.data.HistorialRepository
import com.g001.huertohogar_dam002d.data.PedidoDao
import com.g001.huertohogar_dam002d.data.ProductRepository
import com.g001.huertohogar_dam002d.data.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase) = appDatabase.productDao()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    @Singleton
    fun provideCarritoDao(appDatabase: AppDatabase) = appDatabase.carritoDao()

    @Provides
    @Singleton
    fun providePedidoDao(appDatabase: AppDatabase) = appDatabase.pedidoDao()

    @Provides
    @Singleton
    fun provideProductRepository(productDao: com.g001.huertohogar_dam002d.data.ProductoDao): ProductRepository {
        return ProductRepository(productDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: com.g001.huertohogar_dam002d.data.UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Provides
    @Singleton
    fun provideCarritoRepository(carritoDao: CarritoDao): CarritoRepository {
        return CarritoRepository(carritoDao)
    }

    @Provides
    @Singleton
    fun provideHistorialRepository(pedidoDao: PedidoDao, carritoDao: CarritoDao): HistorialRepository {
        return HistorialRepository(pedidoDao, carritoDao)
    }
}
