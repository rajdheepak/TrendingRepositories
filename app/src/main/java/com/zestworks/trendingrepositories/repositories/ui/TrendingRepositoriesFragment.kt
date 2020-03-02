package com.zestworks.trendingrepositories.repositories.ui

import android.os.Bundle
import android.os.strictmode.Violation
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zestworks.data.model.LCE

import com.zestworks.trendingrepositories.R
import com.zestworks.trendingrepositories.common.ListingAdapter
import com.zestworks.trendingrepositories.repositories.viewmodel.TrendingRepositoriesViewModel
import com.zestworks.trendingrepositories.repositories.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_trending_repositories.*

class TrendingRepositoriesFragment : Fragment() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var trendingRepositoriesViewModel: TrendingRepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositories_shimmer.visibility = View.VISIBLE
        repositories_error.visibility = View.GONE
        repo_list_view.layoutManager = LinearLayoutManager(context)
        repo_list_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        repo_list_view.adapter = ListingAdapter(listOf())
    }

    override fun onStart() {
        super.onStart()
        trendingRepositoriesViewModel = ViewModelFactory.getTrendingRepositoriesViewModel(activity as AppCompatActivity)
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(trendingRepositoriesViewModel.repoState.observeOn(AndroidSchedulers.mainThread()).subscribe {
            when(it) {
                is LCE.YetToMakeRequest -> {
                    repositories_shimmer.visibility = View.VISIBLE
                    repositories_error.visibility = View.GONE
                }
                is LCE.Loading -> {
                    repositories_shimmer.visibility = View.VISIBLE
                    repositories_error.visibility = View.GONE
                }
                is LCE.Content -> {
                    repositories_shimmer.visibility = View.GONE
                    repositories_error.visibility = View.GONE
                    (repo_list_view.adapter as ListingAdapter).updateListItems(it.data)
                }
                is LCE.Error -> {
                    repositories_shimmer.visibility = View.GONE
                    repositories_error.visibility = View.VISIBLE
                }
            }
        })
        trendingRepositoriesViewModel.getTrendingRepos()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }


}
