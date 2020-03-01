package com.zestworks.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zestworks.data.model.TrendingDevelopers
import com.zestworks.data.model.TrendingRepositories

@Database(entities = [TrendingRepositories::class,TrendingDevelopers::class], version = 1)
abstract class TrendingRepositoriesDb: RoomDatabase() {
    abstract val trendingDao: TrendingDao
}