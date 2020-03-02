package com.zestworks.trendingrepositories.developers.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zestworks.trendingrepositories.developers.domain.DeveloperListRepositoryImpl

object ViewModelFactory {
    fun getTrendingRepositoriesViewModel(activity: AppCompatActivity): TrendingDevelopersViewModel {
        return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TrendingDevelopersViewModel(DeveloperListRepositoryImpl()) as T
            }
        })[TrendingDevelopersViewModel::class.java]
    }
}