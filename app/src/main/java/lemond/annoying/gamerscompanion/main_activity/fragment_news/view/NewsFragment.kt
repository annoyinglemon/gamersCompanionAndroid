package lemond.annoying.gamerscompanion.main_activity.fragment_news.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import javax.inject.Inject

import dagger.android.support.AndroidSupportInjection
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModelFactory
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel
import lemond.annoying.gamerscompanion.app.GlideApp
import lemond.annoying.gamerscompanion.databinding.FragmentNewsBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModel
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsItemViewModel


class NewsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsFragmentViewModel
    private lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var newsFragmentViewModelFactory: NewsFragmentViewModelFactory

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        viewModel = ViewModelProviders.of(this, newsFragmentViewModelFactory).get(NewsFragmentViewModel::class.java)

        binding.swipeRefreshNewsFragment.layoutManager = LinearLayoutManager(activity)
        binding.swipeRefreshNewsFragment.setOnRefreshListener(this)

        newsAdapter = NewsAdapter(GlideApp.with(this), getString(R.string.more_news))

        binding.swipeRefreshNewsFragment.setAdapter(newsAdapter)

        mainActivityViewModel.selectedPageLiveData.observe(this, Observer<Int> { this.resetSession(it) })

        viewModel.fetchData(false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getLiveData().observe(this, Observer { listDataWrapper ->
            newsAdapter.setDataList(listDataWrapper?.data as? Collection<NewsItemViewModel>)
            binding.swipeRefreshNewsFragment.setDisplayState(listDataWrapper?.state)
            binding.swipeRefreshNewsFragment.isRefreshing = false
        })
    }


    private fun resetSession(pageSelected: Int?) {
        if (pageSelected != null && pageSelected == 1) {
            binding.swipeRefreshNewsFragment.scrollToTop(mainActivityViewModel.shouldAnimateScrollToTop())
        }
    }

    override fun onRefresh() {
        viewModel.fetchData(true)
    }

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }
}
