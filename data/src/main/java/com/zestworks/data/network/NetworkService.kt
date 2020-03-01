package com.zestworks.data.network

import com.zestworks.data.model.TrendingDevelopers
import com.zestworks.data.model.TrendingRepositories
import retrofit2.http.GET

interface NetworkService {

    @GET("/repositories")
    fun fetchTrendingRepositories(): List<TrendingRepositories>

    @GET("/developers")
    fun fetchTrendingDevelopers(): List<TrendingDevelopers>
}