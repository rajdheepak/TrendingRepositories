package com.zestworks.trendingrepositories.developers.domain

interface DeveloperListRepository {
    fun getTrendingDevelopers(responseListener: DeveloperListRepositoryImpl.TrendingDevelopersResponseListener)

}