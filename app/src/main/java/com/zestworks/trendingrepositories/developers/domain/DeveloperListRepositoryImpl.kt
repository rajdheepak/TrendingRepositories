package com.zestworks.trendingrepositories.developers.domain

import android.annotation.SuppressLint
import com.blankj.utilcode.util.NetworkUtils
import com.zestworks.data.db.TrendingDao
import com.zestworks.data.model.Data
import com.zestworks.data.model.TrendingDevelopers
import com.zestworks.data.network.NetworkService
import com.zestworks.trendingrepositories.dagger.AppComponentProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeveloperListRepositoryImpl: DeveloperListRepository {
    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var trendingDao: TrendingDao

    init {
        AppComponentProvider.appComponent.inject(this)
    }

    @SuppressLint("CheckResult")
    override fun getTrendingDevelopers(responseListener: TrendingDevelopersResponseListener) {
        if(NetworkUtils.isConnected()) {
            networkService.fetchTrendingDevelopers()
                .subscribeOn(Schedulers.io())
                .doOnError {
                    responseListener.onResponse(Data.Error("general error"))
                }
                .observeOn(AndroidSchedulers.mainThread()).subscribe { it ->
                    val body = it.body()
                    if (it.isSuccessful && body != null) {
                        GlobalScope.launch {
                            trendingDao.clearTrendingDevelopers()
                            trendingDao.addTrendingDevelopers(body)
                        }
                        responseListener.onResponse(Data.Success(body))
                    } else {
                        responseListener.onResponse(Data.Error("error response"))
                    }
                }
        } else {
            GlobalScope.launch {
                val trendingDevelopers = trendingDao.getTrendingDevelopers()
                withContext(Dispatchers.Main) {
                    if (trendingDevelopers.isNotEmpty()) {
                        responseListener.onResponse(Data.Success(trendingDevelopers))
                    } else {
                        responseListener.onResponse(Data.Empty)
                    }
                }
            }
        }
    }

    interface TrendingDevelopersResponseListener {
        fun onResponse(data: Data<List<TrendingDevelopers>>)
    }
}