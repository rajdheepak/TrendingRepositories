package com.zestworks.trendingrepositories.developers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zestworks.data.model.LCE
import com.zestworks.trendingrepositories.R
import com.zestworks.trendingrepositories.common.ListingAdapter
import com.zestworks.trendingrepositories.developers.viewmodel.TrendingDevelopersViewModel
import com.zestworks.trendingrepositories.developers.viewmodel.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_trending_developers_framgent.*

class TrendingDevelopersFramgent : Fragment() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var trendingDevelopersViewModel: TrendingDevelopersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trending_developers_framgent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        developer_shimmer.visibility = View.VISIBLE
        developer_error.visibility = View.GONE
        developer_list_view.layoutManager = LinearLayoutManager(context)
        developer_list_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        developer_list_view.adapter = ListingAdapter(listOf())
    }

    override fun onStart() {
        super.onStart()
        trendingDevelopersViewModel = ViewModelFactory.getTrendingRepositoriesViewModel(activity as AppCompatActivity)
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(trendingDevelopersViewModel.developersState.observeOn(AndroidSchedulers.mainThread()).subscribe {
            when(it) {
                is LCE.YetToMakeRequest -> {
                    developer_shimmer.visibility = View.VISIBLE
                    developer_error.visibility = View.GONE
                }
                is LCE.Loading -> {
                    developer_shimmer.visibility = View.VISIBLE
                    developer_error.visibility = View.GONE
                }
                is LCE.Content -> {
                    developer_shimmer.visibility = View.GONE
                    developer_error.visibility = View.GONE
                    (developer_list_view.adapter as ListingAdapter).updateListItems(it.data)
                }
                is LCE.Error -> {
                    developer_shimmer.visibility = View.GONE
                    developer_error.visibility = View.VISIBLE
                }
            }
        })
        trendingDevelopersViewModel.getTrendingDevelopers()
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

}
