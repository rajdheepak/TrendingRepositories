package com.zestworks.trendingrepositories.repositoryDetails.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.zestworks.data.model.TrendingRepositories

import com.zestworks.trendingrepositories.R
import com.zestworks.trendingrepositories.repositoryDetails.domain.RepoDetailsRepositoryImpl
import com.zestworks.trendingrepositories.repositoryDetails.viewmodel.RepoDetailViewModel
import com.zestworks.trendingrepositories.repositoryDetails.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_repo_detail.*

class RepoDetailFragment : Fragment() {

    private lateinit var repoDetailViewModel: RepoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        repoDetailViewModel = ViewModelFactory.getTrendingRepositoriesViewModel(activity as AppCompatActivity)
        if(arguments != null) {
            repoDetailViewModel.getRepoDetails(arguments?.getString("url", " ")!!,object : RepoDetailsRepositoryImpl.repoDetailListener {
                override fun onRepoDetail(trendingRepositories: TrendingRepositories) {
                    url.text = trendingRepositories.url
                    language_text.text = trendingRepositories.language
                    star_text.text = trendingRepositories.stars.toString()
                    fork_text.text = trendingRepositories.forks.toString()
                }

            })
        }
    }


}
