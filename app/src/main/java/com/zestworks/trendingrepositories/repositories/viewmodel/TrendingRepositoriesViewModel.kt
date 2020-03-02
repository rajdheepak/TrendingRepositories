package com.zestworks.trendingrepositories.repositories.viewmodel

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.zestworks.data.model.Data
import com.zestworks.data.model.LCE
import com.zestworks.data.model.TrendingRepositories
import com.zestworks.trendingrepositories.repositories.domain.RepoListRepository
import com.zestworks.trendingrepositories.repositories.domain.RepoListRepositoryImpl

class TrendingRepositoriesViewModel(val reposListRepository: RepoListRepository): ViewModel() {

    val repoState: BehaviorRelay<LCE<List<TrendingRepositories>>> = BehaviorRelay.create()

    init {
        repoState.accept(LCE.YetToMakeRequest)
    }

    fun getTrendingRepos() {
        if(repoState.value is LCE.YetToMakeRequest || repoState.value is LCE.Error) {
            repoState.accept(LCE.Loading)
            reposListRepository.getTrendingRepositories(object : RepoListRepositoryImpl.TrendingReposResponseListener {
                override fun onResponse(data: Data<List<TrendingRepositories>>) {
                    when(data) {
                        is Data.Success -> {
                            repoState.accept(LCE.Content(data = data.data))
                        }
                        is Data.Error -> {
                            repoState.accept(LCE.Error(data.errorMessage))
                        }
                        is Data.Empty -> {
                            repoState.accept(LCE.Error("empty list response"))
                        }
                    }
                }

            })
        }
    }

}