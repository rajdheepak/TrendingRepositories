package com.zestworks.trendingrepositories

import com.zestworks.data.model.Data
import com.zestworks.data.model.LCE
import com.zestworks.data.model.TrendingDevelopers
import com.zestworks.trendingrepositories.developers.domain.DeveloperListRepository
import com.zestworks.trendingrepositories.developers.domain.DeveloperListRepositoryImpl
import com.zestworks.trendingrepositories.developers.viewmodel.TrendingDevelopersViewModel
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingDevelopersViewmodelTest {

    @Mock
    lateinit var developerListRepository: DeveloperListRepository

    lateinit var trendingDevelopersViewModel: TrendingDevelopersViewModel

    val developersStateObserver = TestObserver<LCE<List<TrendingDevelopers>>>()

    @Captor
    lateinit var trendingDevelopersResponseListener: ArgumentCaptor<DeveloperListRepositoryImpl.TrendingDevelopersResponseListener>

    @Before
    fun setup() {
        trendingDevelopersViewModel = TrendingDevelopersViewModel(developerListRepository)
        trendingDevelopersViewModel.developersState.subscribe(developersStateObserver)
    }

    @Test
    fun `Throwing loading when network request in flight`() {
        trendingDevelopersViewModel.getTrendingDevelopers()
        Mockito.verify(developerListRepository).getTrendingDevelopers(capture(trendingDevelopersResponseListener))
        val stateAfterNetworkFailure = developersStateObserver.values().last()
        (stateAfterNetworkFailure is LCE.Loading) shouldBe true
    }

    @Test
    fun `Throwing error when network request fails`() {
        trendingDevelopersViewModel.getTrendingDevelopers()
        Mockito.verify(developerListRepository).getTrendingDevelopers(capture(trendingDevelopersResponseListener))
        val error = "general message"
        trendingDevelopersResponseListener.value.onResponse(Data.Error(error))
        val stateAfterNetworkFailure = developersStateObserver.values().last()
        (stateAfterNetworkFailure is LCE.Error) shouldBe true
        (stateAfterNetworkFailure as LCE.Error).errorMessage shouldBe error
    }

    @Test
    fun `Throwing success when network request passes`() {
        trendingDevelopersViewModel.getTrendingDevelopers()
        Mockito.verify(developerListRepository).getTrendingDevelopers(capture(trendingDevelopersResponseListener))
        trendingDevelopersResponseListener.value.onResponse(Data.Success(listOf()))
        val stateAfterNetworkFailure = developersStateObserver.values().last()
        (stateAfterNetworkFailure is LCE.Content) shouldBe true
    }
}