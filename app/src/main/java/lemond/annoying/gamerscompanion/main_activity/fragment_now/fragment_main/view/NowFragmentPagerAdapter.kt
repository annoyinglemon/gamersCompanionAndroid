package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.HypedPageFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.PopularPageFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.TrendingPageFragment

class NowFragmentPagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            FRAGMENT_POPULAR_POSITION -> return TAB_TEXT_POPULAR
            FRAGMENT_HYPED_POSITION -> return TAB_TEXT_HYPED
            FRAGMENT_TRENDING_POSITION -> return TAB_TEXT_TRENDING
            else -> return TAB_TEXT_TRENDING
        }
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            FRAGMENT_POPULAR_POSITION -> return PopularPageFragment.newInstance()
            FRAGMENT_HYPED_POSITION -> return HypedPageFragment.newInstance()
            FRAGMENT_TRENDING_POSITION -> return TrendingPageFragment.newInstance()
            else -> return TrendingPageFragment.newInstance()
        }
    }

    companion object {

        private val FRAGMENT_TRENDING_POSITION = 0
        private val FRAGMENT_POPULAR_POSITION = 1
        private val FRAGMENT_HYPED_POSITION = 2

        private val TAB_TEXT_TRENDING = "trending"
        private val TAB_TEXT_POPULAR = "popular"
        private val TAB_TEXT_HYPED = "hyped"
    }
}
