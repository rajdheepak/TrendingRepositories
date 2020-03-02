package com.zestworks.trendingrepositories.repositories.domain

interface RepoListRepository {
    fun getTrendingRepositories(responseListener: RepoListRepositoryImpl.TrendingReposResponseListener)
}