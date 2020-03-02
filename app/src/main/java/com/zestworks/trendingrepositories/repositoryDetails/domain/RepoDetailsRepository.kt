package com.zestworks.trendingrepositories.repositoryDetails.domain

interface RepoDetailsRepository {
    fun getRepoDetail(url: String, repoDetailListener: RepoDetailsRepositoryImpl.repoDetailListener)
}