package com.zestworks.trendingrepositories.dagger

import android.app.Application
import androidx.room.Room
import com.zestworks.data.db.TrendingDao
import com.zestworks.data.db.TrendingRepositoriesDb
import com.zestworks.data.network.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): NetworkService {
        return Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(application: Application): TrendingDao {
        return Room.databaseBuilder(application,TrendingRepositoriesDb::class.java,"trending").build().trendingDao
    }

}