package com.zestworks.data.network

import com.zestworks.data.model.TrendingRepositories
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("/repositories")
    fun fetchTrendingRepositories(): Single<Response<List<TrendingRepositories>>>

//    @GET("/developers")
//    fun fetchTrendingDevelopers(): List<TrendingDevelopers>
}