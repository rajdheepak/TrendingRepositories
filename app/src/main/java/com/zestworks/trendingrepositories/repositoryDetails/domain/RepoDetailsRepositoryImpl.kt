package com.zestworks.trendingrepositories.repositoryDetails.domain

import com.zestworks.data.db.TrendingDao
import com.zestworks.data.model.TrendingRepositories
import com.zestworks.trendingrepositories.dagger.AppComponentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoDetailsRepositoryImpl: RepoDetailsRepository {

    init {
        AppComponentProvider.appComponent.inject(this)
    }

    @Inject
    lateinit var trendingDao: TrendingDao

    override fun getRepoDetail(url: String, repoDetailListener: repoDetailListener) {
        GlobalScope.launch {
            val repoDetails = trendingDao.getRepoDetails(url)
            withContext(Dispatchers.Main) {
                repoDetailListener.onRepoDetail(repoDetails)
            }
        }
    }

    interface repoDetailListener {
        fun onRepoDetail(trendingRepositories: TrendingRepositories)
    }
}