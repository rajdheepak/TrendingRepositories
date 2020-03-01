package com.zestworks.trendingrepositories.dagger

object AppComponentProvider {
    lateinit var appComponent: AppComponent

    fun appComponent(): AppComponent {
        return appComponent
    }
}