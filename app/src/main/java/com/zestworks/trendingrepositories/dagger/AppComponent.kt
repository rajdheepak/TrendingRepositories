package com.zestworks.trendingrepositories.dagger

import android.app.Application
import com.zestworks.trendingrepositories.TrendingApplication
import com.zestworks.trendingrepositories.developers.domain.DeveloperListRepositoryImpl
import com.zestworks.trendingrepositories.repositories.domain.RepoListRepositoryImpl
import com.zestworks.trendingrepositories.repositoryDetails.domain.RepoDetailsRepositoryImpl
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {

    fun inject(trendingApplication: TrendingApplication)

    fun inject(trendingApplication: RepoListRepositoryImpl)

    fun inject(developerListRepositoryImpl: DeveloperListRepositoryImpl)

    fun inject(repoDetailsRepositoryImpl: RepoDetailsRepositoryImpl)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}