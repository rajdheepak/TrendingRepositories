package com.zestworks.trendingrepositories.repositories.domain

import android.annotation.SuppressLint
import com.blankj.utilcode.util.NetworkUtils
import com.zestworks.data.db.TrendingDao
import com.zestworks.data.model.Data
import com.zestworks.data.model.TrendingRepositories
import com.zestworks.data.network.NetworkService
import com.zestworks.trendingrepositories.dagger.AppComponentProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoListRepositoryImpl: RepoListRepository {

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var trendingDao: TrendingDao

    init {
        AppComponentProvider.appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun getTrendingRepositories(responseListener: TrendingReposResponseListener) {
        if(NetworkUtils.isConnected()) {
            networkService.fetchTrendingRepositories()
                .subscribeOn(Schedulers.io())
                .doOnError {
                    responseListener.onResponse(Data.Error("general error"))
                }
                .observeOn(AndroidSchedulers.mainThread()).subscribe { it ->
                    val body = it.body()
                    if (it.isSuccessful && body != null) {
                        GlobalScope.launch {
                            trendingDao.clearTrendingRepos()
                            trendingDao.addTrendingRepositories(body)
                        }
                        responseListener.onResponse(Data.Success(body))
                    } else {
                        responseListener.onResponse(Data.Error("error response"))
                    }
                }
        } else {
            GlobalScope.launch {
                val trendingRepositories = trendingDao.getTrendingRepositories()
                withContext(Dispatchers.Main) {
                    if (trendingRepositories.isNotEmpty()) {
                        responseListener.onResponse(Data.Success(trendingRepositories))
                    } else {
                        responseListener.onResponse(Data.Empty)
                    }
                }
            }
        }
    }

    interface TrendingReposResponseListener {
        fun onResponse(data: Data<List<TrendingRepositories>>)
    }

}