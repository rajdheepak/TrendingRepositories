package com.zestworks.trendingrepositories

import android.app.Application
import androidx.fragment.app.Fragment
import com.zestworks.trendingrepositories.dagger.AppComponentProvider
import com.zestworks.trendingrepositories.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class TrendingApplication: Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        AppComponentProvider.appComponent = appComponent
    }
}