package com.zestworks.trendingrepositories.developers.viewmodel

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.zestworks.data.model.Data
import com.zestworks.data.model.LCE
import com.zestworks.data.model.TrendingDevelopers
import com.zestworks.trendingrepositories.developers.domain.DeveloperListRepository
import com.zestworks.trendingrepositories.developers.domain.DeveloperListRepositoryImpl

class TrendingDevelopersViewModel(val developerListRepository: DeveloperListRepository): ViewModel() {


    val developersState: BehaviorRelay<LCE<List<TrendingDevelopers>>> = BehaviorRelay.create()

    init {
        developersState.accept(LCE.YetToMakeRequest)
    }

    fun getTrendingDevelopers() {
        if(developersState.value is LCE.YetToMakeRequest || developersState.value is LCE.Error) {
            developersState.accept(LCE.Loading)
            developerListRepository.getTrendingDevelopers(object: DeveloperListRepositoryImpl.TrendingDevelopersResponseListener {
                override fun onResponse(data: Data<List<TrendingDevelopers>>) {
                    when(data) {
                        is Data.Success -> {
                            developersState.accept(LCE.Content(data = data.data))
                        }
                        is Data.Error -> {
                            developersState.accept(LCE.Error(data.errorMessage))
                        }
                        is Data.Empty -> {
                            developersState.accept(LCE.Error("empty list response"))
                        }
                    }
                }

            })
        }
    }

}