package com.zestworks.trendingrepositories


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.zestworks.trendingrepositories.developers.ui.TrendingDevelopersFramgent
import com.zestworks.trendingrepositories.repositories.ui.TrendingRepositoriesFragment
import java.util.ArrayList

class TrendingPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = PagerAdapter(childFragmentManager)
        val trendingRepositoriesFragment = TrendingRepositoriesFragment()
        val trendingDevelopersFragment = TrendingDevelopersFramgent()
        pagerAdapter.addFragment(trendingRepositoriesFragment)
        pagerAdapter.addFragment(trendingDevelopersFragment)

        val formatPager = getView()!!.findViewById<ViewPager>(R.id.pager)
        formatPager.adapter = pagerAdapter

        val formatTab = getView()!!.findViewById<TabLayout>(R.id.tab)
        formatTab.setupWithViewPager(formatPager)
    }


    inner class PagerAdapter internal constructor(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        private val formatFragmentList = ArrayList<Fragment>()

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            return formatFragmentList[position]
        }

        internal fun addFragment(fragment: androidx.fragment.app.Fragment) {
            formatFragmentList.add(fragment)
        }

        override fun getCount(): Int {
            return formatFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return getString(R.string.repositories)
                1 -> return getString(R.string.developers)
                else -> return null
            }
        }
    }

}
