package com.zestworks.trendingrepositories.repositoryDetails.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zestworks.trendingrepositories.repositoryDetails.domain.RepoDetailsRepositoryImpl

object ViewModelFactory {
    fun getTrendingRepositoriesViewModel(activity: AppCompatActivity): RepoDetailViewModel {
        return ViewModelProviders.of(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RepoDetailViewModel(RepoDetailsRepositoryImpl()) as T
            }
        })[RepoDetailViewModel::class.java]
    }
}