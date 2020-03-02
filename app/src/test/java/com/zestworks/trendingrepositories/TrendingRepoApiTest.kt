package com.zestworks.trendingrepositories

import com.zestworks.data.network.NetworkService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TrendingRepoApiTest: MockGithubApi<NetworkService>() {
    private lateinit var networkService: NetworkService

    @Before
    fun init() {
        networkService = createService(NetworkService::class.java)
    }

    @Test
    fun fetchPostTests() {
        enqueueResponse("apiresponse.json")
        val fetchWeatherForecast = networkService.fetchTrendingRepositories().blockingGet()
        Assert.assertEquals(25, fetchWeatherForecast.body()?.size)
    }
}