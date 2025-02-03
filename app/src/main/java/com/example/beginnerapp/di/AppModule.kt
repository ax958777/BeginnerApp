package com.example.beginnerapp.di

import android.content.Context
import androidx.room.Room
import com.example.beginnerapp.network.Constants
import com.example.beginnerapp.network.service.ApiService
import com.example.beginnerapp.room.DAO.CartItemDao
import com.example.beginnerapp.room.DAO.ProductDao
import com.example.beginnerapp.room.database.ShoppingCartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit:Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    //Room
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context): ShoppingCartDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingCartDatabase::class.java,
            "shopping_cart_database"
        ).build()
    }

    @Provides
    fun provideProductDao(database:ShoppingCartDatabase):ProductDao{
        return database.productDao()
    }

    @Provides
    fun provideCartItemDao(database:ShoppingCartDatabase): CartItemDao {
        return database.cartItemDao()
    }
}