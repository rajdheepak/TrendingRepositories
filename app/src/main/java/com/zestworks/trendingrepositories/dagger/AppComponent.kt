package com.zestworks.trendingrepositories.dagger

import android.app.Application
import com.zestworks.trendingrepositories.TrendingApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AppModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {

    fun inject(trendingApplication: TrendingApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}