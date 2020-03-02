package com.zestworks.trendingrepositories.repositories.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zestworks.trendingrepositories.repositories.domain.RepoListRepositoryImpl

object ViewModelFactory {
    fun getTrendingRepositoriesViewModel(activity: AppCompatActivity): TrendingRepositoriesViewModel {
        return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TrendingRepositoriesViewModel(RepoListRepositoryImpl()) as T
            }
        })[TrendingRepositoriesViewModel::class.java]
    }
}