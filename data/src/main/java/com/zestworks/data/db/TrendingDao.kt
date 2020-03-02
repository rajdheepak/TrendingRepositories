package com.zestworks.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zestworks.data.model.TrendingRepositories

@Dao
interface TrendingDao {

    @Query("SELECT * from repositories")
    fun getTrendingRepositories(): List<TrendingRepositories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTrendingRepositories(trendingRepositories: List<TrendingRepositories>)

    @Query("DELETE FROM repositories")
    fun clearTrendingRepos()
}