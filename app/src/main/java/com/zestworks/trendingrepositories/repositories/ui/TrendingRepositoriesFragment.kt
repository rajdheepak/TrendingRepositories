package com.zestworks.trendingrepositories.repositories.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zestworks.trendingrepositories.R

/**
 * A simple [Fragment] subclass.
 */
class TrendingRepositoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending_repositories, container, false)
    }


}
