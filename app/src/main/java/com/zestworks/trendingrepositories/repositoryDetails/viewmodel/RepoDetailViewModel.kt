package com.zestworks.trendingrepositories.repositoryDetails.viewmodel

import androidx.lifecycle.ViewModel
import com.zestworks.trendingrepositories.repositoryDetails.domain.RepoDetailsRepository
import com.zestworks.trendingrepositories.repositoryDetails.domain.RepoDetailsRepositoryImpl

class RepoDetailViewModel(val repoDetailsRepository: RepoDetailsRepository): ViewModel() {

    fun getRepoDetails(url: String, repoDetailListener: RepoDetailsRepositoryImpl.repoDetailListener) {
        return repoDetailsRepository.getRepoDetail(url, repoDetailListener)
    }

}