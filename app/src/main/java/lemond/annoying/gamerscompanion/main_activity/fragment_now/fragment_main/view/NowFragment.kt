package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import dagger.android.support.AndroidSupportInjection
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.PopularPageFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel
import lemond.annoying.gamerscompanion.databinding.FragmentNowBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.HypedPageFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.TrendingPageFragment


class NowFragment : Fragment(), TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private lateinit var binding: FragmentNowBinding

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

//    https://stackoverflow.com/questions/48103894/kotlin-dagger2-cannot-be-provided-without-an-inject-constructor-or-from-an?rq=1
    @Inject
    @field:TrendingPage
    lateinit var trendingViewModelFactory: NowPageViewModelFactory

    @Inject
    @field:PopularPage
    lateinit var popularViewModelFactory: NowPageViewModelFactory

    @Inject
    @field:HypedPage
    lateinit var hypedViewModelFactory: NowPageViewModelFactory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now, container, false)

        val mainFragmentPagerAdapter = NowFragmentPagerAdapter(childFragmentManager)

        binding.viewpagerNowFragment.adapter = mainFragmentPagerAdapter

        binding.tablayoutNowFragment.addOnTabSelectedListener(this)
        binding.viewpagerNowFragment.addOnPageChangeListener(this)

        binding.tablayoutNowFragment.setupWithViewPager(binding.viewpagerNowFragment)

        return binding.root
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        when (childFragment) {
            is TrendingPageFragment -> {
                val viewModel = ViewModelProviders.of(this, trendingViewModelFactory)
                        .get(NowPageRepository.PageType.TRENDING.toString(), NowPageFragmentViewModel::class.java)
                childFragment.setViewModels(mainActivityViewModel, viewModel)

            }
            is PopularPageFragment -> {
                val viewModel = ViewModelProviders.of(this, popularViewModelFactory)
                        .get(NowPageRepository.PageType.POPULAR.toString(), NowPageFragmentViewModel::class.java)
                childFragment.setViewModels(mainActivityViewModel, viewModel)

            }
            else -> {
                val viewModel = ViewModelProviders.of(this, hypedViewModelFactory)
                        .get(NowPageRepository.PageType.HYPED.toString(), NowPageFragmentViewModel::class.java)
                (childFragment as HypedPageFragment).setViewModels(mainActivityViewModel, viewModel)
            }
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        binding.viewpagerNowFragment.currentItem = tab.position
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {}

    override fun onTabReselected(tab: TabLayout.Tab) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        val tab = binding.tablayoutNowFragment.getTabAt(position)
        tab?.select()
    }

    override fun onPageScrollStateChanged(state: Int) {}

    companion object {

        fun newInstance(): NowFragment {
            return NowFragment()
        }
    }
}
