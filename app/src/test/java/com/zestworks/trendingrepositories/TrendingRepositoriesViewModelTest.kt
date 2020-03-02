package com.zestworks.trendingrepositories

import com.zestworks.data.model.Data
import com.zestworks.data.model.LCE
import com.zestworks.data.model.TrendingRepositories
import com.zestworks.trendingrepositories.repositories.domain.RepoListRepository
import com.zestworks.trendingrepositories.repositories.domain.RepoListRepositoryImpl
import com.zestworks.trendingrepositories.repositories.viewmodel.TrendingRepositoriesViewModel
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingRepositoriesViewModelTest {

    @Mock
    lateinit var repoListRepository: RepoListRepository

    lateinit var trendingRepositoriesViewModel: TrendingRepositoriesViewModel

    val repoStateObserver = TestObserver<LCE<List<TrendingRepositories>>>()

    @Captor
    lateinit var trendingReposResponseListener: ArgumentCaptor<RepoListRepositoryImpl.TrendingReposResponseListener>

    @Before
    fun setup() {
        trendingRepositoriesViewModel = TrendingRepositoriesViewModel(repoListRepository)
        trendingRepositoriesViewModel.repoState.subscribe(repoStateObserver)
    }

    @Test
    fun `Throwing loading when network request in flight`() {
        trendingRepositoriesViewModel.getTrendingRepos()
        verify(repoListRepository).getTrendingRepositories(capture(trendingReposResponseListener))
        val stateAfterNetworkFailure = repoStateObserver.values().last()
        (stateAfterNetworkFailure is LCE.Loading) shouldBe true
    }

    @Test
    fun `Throwing error when network request fails`() {
        trendingRepositoriesViewModel.getTrendingRepos()
        verify(repoListRepository).getTrendingRepositories(capture(trendingReposResponseListener))
        val error = "general message"
        trendingReposResponseListener.value.onResponse(Data.Error(error))
        val stateAfterNetworkFailure = repoStateObserver.values().last()
        (stateAfterNetworkFailure is LCE.Error) shouldBe true
        (stateAfterNetworkFailure as LCE.Error).errorMessage shouldBe error
    }

    @Test
    fun `Throwing success when network request passes`() {
        trendingRepositoriesViewModel.getTrendingRepos()
        verify(repoListRepository).getTrendingRepositories(capture(trendingReposResponseListener))
        trendingReposResponseListener.value.onResponse(Data.Success(listOf()))
        val stateAfterNetworkFailure = repoStateObserver.values().last()
        (stateAfterNetworkFailure is LCE.Content) shouldBe true
    }
}